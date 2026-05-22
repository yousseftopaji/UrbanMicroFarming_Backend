package dk.via.group1.urbanmicrofarm_backend.application.services.watering;

import dk.via.group1.urbanmicrofarm_backend.application.domain.GrowingSetup;
import dk.via.group1.urbanmicrofarm_backend.application.domain.SensorReading;
import dk.via.group1.urbanmicrofarm_backend.application.services.sensor_reading_service.SensorReadingQueryService;
import dk.via.group1.urbanmicrofarm_backend.application.services.sensor_reading_service.SensorReadingQueryServiceImpl;
import dk.via.group1.urbanmicrofarm_backend.application.services.sensor_reading_service.SensorReadingService;
import dk.via.group1.urbanmicrofarm_backend.database.entities.*;
import dk.via.group1.urbanmicrofarm_backend.database.repository.*;
import dk.via.group1.urbanmicrofarm_backend.dto.WateringEventResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionRequestDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mqttDto.ActuatorCommandDto;
import dk.via.group1.urbanmicrofarm_backend.mapper.mlMapper.WaterPredictionMapper;
import dk.via.group1.urbanmicrofarm_backend.mlClient.MLPredictionClient;
import dk.via.group1.urbanmicrofarm_backend.mqtt.publisher.MqttPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WateringServiceImpl implements WateringService {

    private static final int DEFAULT_MANUAL_WATERING_AMOUNT_ML = 50;

    private final PlantRepository plantRepository;
    private final GrowingSetupRepository growingSetupRepository;
    private final SensorRepository sensorRepository;
    private final SensorReadingQueryService sensorReadingQueryService;
    private final ActuatorRepository actuatorRepository;
    private final WateringEventRepository wateringEventRepository;
    private final MLPredictionClient mlPredictionClient;
    private final MqttPublisher mqttPublisher;
    private final ObjectMapper objectMapper;

    public WateringServiceImpl(
            PlantRepository plantRepository,
            GrowingSetupRepository growingSetupRepository,
            SensorRepository sensorRepository,
            SensorReadingQueryService sensorReadingQueryService,
            ActuatorRepository actuatorRepository,
            WateringEventRepository wateringEventRepository,
            MLPredictionClient mlPredictionClient,
            MqttPublisher mqttPublisher,
            ObjectMapper objectMapper) {
        this.plantRepository = plantRepository;
        this.sensorRepository = sensorRepository;
        this.growingSetupRepository = growingSetupRepository;
        this.sensorReadingQueryService = sensorReadingQueryService;
        this.actuatorRepository = actuatorRepository;
        this.wateringEventRepository = wateringEventRepository;
        this.mlPredictionClient = mlPredictionClient;
        this.mqttPublisher = mqttPublisher;
        this.objectMapper = objectMapper;
    }

    @Override
    public void triggerManualWatering(Long plantId) {
        PlantEntity plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));

        SensorEntity sensor = sensorRepository.findById(plant.getSensorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor not found for plant"));

        int setupId = sensor.getSetupId();

        List<SensorEntity> sensorsList = sensorRepository.findBySetupId(setupId);

        SensorEntity soilMoistureSensor = sensorsList.stream()
                .filter(s -> "Soil_Moisture".equalsIgnoreCase(s.getSensorTypeName()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soil moisture sensor not found"));
        SensorEntity humiditySensor = sensorsList.stream()
                .filter(s -> "Humidity".equals(s.getSensorTypeName()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Humidity sensor not found"));
        SensorEntity ligthSensor = sensorsList.stream()
                .filter(s -> "Light".equals(s.getSensorTypeName()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Light sensor not found"));

        SensorEntity temperatureSensor = sensorsList.stream()
                .filter(s ->"Temperature".equals(s.getSensorTypeName()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Temperature sensor not found"));

//        get all latest readings to sent them to ML
        SensorReading soilMoisture = sensorReadingQueryService.getLatestReading(soilMoistureSensor.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor not found for plant"));
        SensorReading humidity = sensorReadingQueryService.getLatestReading(humiditySensor.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor not found for plant"));
        SensorReading light = sensorReadingQueryService.getLatestReading(ligthSensor.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor not found for plant"));
        SensorReading temperature = sensorReadingQueryService.getLatestReading(temperatureSensor.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor not found for plant"));

        ActuatorEntity actuator = actuatorRepository.findFirstBySetupId(setupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actuator found for setup " + setupId));

        // we map telemetry values to ML request DTO
        WaterPredictionMapper waterPredictionMapper;
        WaterPredictionRequestDto request = new WaterPredictionRequestDto(
                temperature.getValue(),
                humidity.getValue(),
                (int)light.getValue(),
                soilMoisture.getValue()
        );

        // we call ML serverless function to get predicted watering amount
        WaterPredictionResponseDto response = mlPredictionClient.predictWater(request);

        // we send the predicted watering amount back to IoT through MQTT
        publishWaterCommand(
                setupId,
                response.wateringAmount()
        );

//        save the watering event as manual
        WateringEventEntity event = new WateringEventEntity();
        event.setActuatorId(actuator.getId());
        event.setWaterUsed_mL((double) response.wateringAmount());
        event.setMode("manual");
        wateringEventRepository.save(event);
    }

    @Override
    public Optional<WateringEventResponseDto> getLatestWateringEvent(int setupId) {
        return wateringEventRepository
                .findTopByActuator_SetupIdOrderByCreatedAtDesc(setupId)
                .map(this::toDto);
    }

    @Override
    public List<WateringEventResponseDto> getWateringEventHistory(int setupId, Instant from, Instant to) {
        Instant start = from != null ? from : Instant.EPOCH;
        Instant end = to != null ? to : Instant.now();
        return wateringEventRepository
                .findByActuator_SetupIdAndCreatedAtBetween(setupId, start, end)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private void publishWaterCommand(int setupId, int amountMl) {
        String topic = "farm/" + setupId + "/cmd";
        ActuatorCommandDto command = new ActuatorCommandDto("water_pump", amountMl);
        try {
            String payload = objectMapper.writeValueAsString(command);
            mqttPublisher.publish(topic, payload);
        } catch (Exception e) {
            throw new IllegalStateException("Could not publish actuator command", e);
        }
    }

    private WateringEventResponseDto toDto(WateringEventEntity e) {
        return new WateringEventResponseDto(e.getId(), e.getWaterUsed_mL(), e.getMode(), e.getCreatedAt());
    }
}

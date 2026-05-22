package dk.via.group1.urbanmicrofarm_backend.application.services.watering;

import dk.via.group1.urbanmicrofarm_backend.database.entities.ActuatorEntity;
import dk.via.group1.urbanmicrofarm_backend.database.entities.PlantEntity;
import dk.via.group1.urbanmicrofarm_backend.database.entities.SensorEntity;
import dk.via.group1.urbanmicrofarm_backend.database.entities.WateringEventEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.ActuatorRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.PlantRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SensorRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.WateringEventRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.WateringEventResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mqttDto.ActuatorCommandDto;
import dk.via.group1.urbanmicrofarm_backend.mqtt.publisher.MqttPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class WateringServiceImpl implements WateringService {

    private static final int DEFAULT_MANUAL_WATERING_AMOUNT_ML = 200;

    private final PlantRepository plantRepository;
    private final SensorRepository sensorRepository;
    private final ActuatorRepository actuatorRepository;
    private final WateringEventRepository wateringEventRepository;
    private final MqttPublisher mqttPublisher;
    private final ObjectMapper objectMapper;

    public WateringServiceImpl(
            PlantRepository plantRepository,
            SensorRepository sensorRepository,
            ActuatorRepository actuatorRepository,
            WateringEventRepository wateringEventRepository,
            MqttPublisher mqttPublisher,
            ObjectMapper objectMapper) {
        this.plantRepository = plantRepository;
        this.sensorRepository = sensorRepository;
        this.actuatorRepository = actuatorRepository;
        this.wateringEventRepository = wateringEventRepository;
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

        ActuatorEntity actuator = actuatorRepository.findFirstBySetupId(setupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actuator found for setup " + setupId));

        publishWaterCommand(setupId, DEFAULT_MANUAL_WATERING_AMOUNT_ML);

        WateringEventEntity event = new WateringEventEntity();
        event.setActuatorId(actuator.getId());
        event.setWaterUsed_mL((double) DEFAULT_MANUAL_WATERING_AMOUNT_ML);
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

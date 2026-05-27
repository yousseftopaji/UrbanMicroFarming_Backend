package dk.via.group1.urbanmicrofarm_backend.application.services.watering;

import dk.via.group1.urbanmicrofarm_backend.database.entities.WateringEventEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.WateringEventRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.TelemetryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionRequestDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mqttDto.ActuatorCommandDto;
import dk.via.group1.urbanmicrofarm_backend.mapper.dbMapper.WateringEventDbMapper;
import dk.via.group1.urbanmicrofarm_backend.mapper.mlMapper.WaterPredictionMapper;
import dk.via.group1.urbanmicrofarm_backend.mlClient.MLPredictionClient;
import dk.via.group1.urbanmicrofarm_backend.mqtt.publisher.MqttPublisher;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class WateringAutomationServiceImpl implements WateringAutomationService {

    private static final Logger log = LoggerFactory.getLogger(WateringAutomationServiceImpl.class);
    private static final double SOIL_MOISTURE_THRESHOLD_PERCENT = 20.0;

    private final WaterPredictionMapper waterPredictionMapper;
    private final MLPredictionClient mlPredictionClient;
    private final MqttPublisher mqttPublisher;
    private final ObjectMapper objectMapper;
    private final WateringEventRepository wateringEventRepository;
    private final WateringEventDbMapper wateringEventDbMapper;

    public WateringAutomationServiceImpl(
            WaterPredictionMapper waterPredictionMapper,
            MLPredictionClient mlPredictionClient,
            MqttPublisher mqttPublisher,
            ObjectMapper objectMapper, WateringEventRepository wateringEventRepository, WateringEventDbMapper wateringEventDbMapper) {
        this.waterPredictionMapper = waterPredictionMapper;
        this.mlPredictionClient = mlPredictionClient;
        this.mqttPublisher = mqttPublisher;
        this.objectMapper = objectMapper;
        this.wateringEventRepository = wateringEventRepository;
        this.wateringEventDbMapper = wateringEventDbMapper;
    }

    @Override
    public void handleWateringIfNeeded(TelemetryData telemetryData) {
        double soilMoisturePercent = convertSoilMoistureToPercent(telemetryData.soilMoisture());

        if (soilMoisturePercent >= SOIL_MOISTURE_THRESHOLD_PERCENT) {
            return;
        }

        double temperature = telemetryData.temperature() / 10.0;
        double humidity = telemetryData.humidity() / 10.0;

        WaterPredictionRequestDto request = waterPredictionMapper.toRequestDto(
                temperature,
                humidity,
                telemetryData.light(),
                telemetryData.soilMoisture()
        );

        WaterPredictionResponseDto response = mlPredictionClient.predictWater(request);

        WateringEventEntity prediction = wateringEventDbMapper.toAutomaticEntity(response, 1L);
        wateringEventRepository.save(prediction);

        publishWaterCommand(telemetryData.setupId(), response.wateringAmount());
    }

    private void publishWaterCommand(int setupId, int amountMl) {
        String topic = "farm/" + setupId + "/cmd";
        ActuatorCommandDto command = new ActuatorCommandDto("water_pump", amountMl);
        try {
            String payload = objectMapper.writeValueAsString(command);
            log.info("Publishing MQTT command: topic={}, payload={}", topic, payload);
            mqttPublisher.publish(topic, payload);
        } catch (Exception e) {
            throw new IllegalStateException("Could not publish actuator command", e);
        }
    }

    private double convertSoilMoistureToPercent(int rawSoilMoisture) {
        return (rawSoilMoisture / 1023.0) * 100.0;
    }
}
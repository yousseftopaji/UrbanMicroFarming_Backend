package dk.via.group1.urbanmicrofarm_backend.application.services.sensor_reading_service;

import dk.via.group1.urbanmicrofarm_backend.application.domain.SensorReading;
import dk.via.group1.urbanmicrofarm_backend.application.services.watering.WateringAutomationService;
import dk.via.group1.urbanmicrofarm_backend.database.entities.SensorReadingEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SensorReadingRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.TelemetryData;
import dk.via.group1.urbanmicrofarm_backend.mapper.dbMapper.SensorReadingPersistenceMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository sensorReadingRepository;
    private final SensorReadingPersistenceMapper sensorReadingPersistenceMapper;
    private final WateringAutomationService wateringAutomationService;

    public SensorReadingServiceImpl(
            SensorReadingRepository sensorReadingRepository,
            SensorReadingPersistenceMapper sensorReadingPersistenceMapper,
            WateringAutomationService wateringAutomationService) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.sensorReadingPersistenceMapper = sensorReadingPersistenceMapper;
        this.wateringAutomationService = wateringAutomationService;
    }

    @Override
    public void processReadings(TelemetryData telemetryData) {
        validate(telemetryData);

        Instant timestamp = Instant.now();

        double temperature = telemetryData.temperature() / 10.0;
        double humidity = telemetryData.humidity() / 10.0;
        int light = telemetryData.light();
        int soilMoistureRaw = telemetryData.soilMoisture();

        List<SensorReading> readings = createSensorReadings(timestamp, temperature, humidity, light, soilMoistureRaw);

        List<SensorReadingEntity> entities = readings.stream()
                .map(reading -> sensorReadingPersistenceMapper.toEntity(reading))
                .toList();

        sensorReadingRepository.saveAll(entities);

        wateringAutomationService.handleWateringIfNeeded(telemetryData);
    }

    private List<SensorReading> createSensorReadings(
            Instant timestamp,
            double temperature,
            double humidity,
            int light,
            int soilMoistureRaw) {

        List<SensorReading> readings = new ArrayList<>();

        readings.add(new SensorReading(temperature, timestamp, 1));
        readings.add(new SensorReading(humidity, timestamp, 4));
        readings.add(new SensorReading(light, timestamp, 2));
        readings.add(new SensorReading(soilMoistureRaw, timestamp, 3));

        return readings;
    }

    private void validate(TelemetryData telemetryData) {
        if (telemetryData.setupId() <= 0) {
            throw new IllegalArgumentException("Invalid setup id");
        }

        if (telemetryData.sensorId() != null && telemetryData.sensorId() <= 0) {
            throw new IllegalArgumentException("Invalid sensor id");
        }
    }
}

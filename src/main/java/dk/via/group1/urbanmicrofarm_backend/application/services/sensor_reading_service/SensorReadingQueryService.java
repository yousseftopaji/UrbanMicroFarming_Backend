package dk.via.group1.urbanmicrofarm_backend.application.services.sensor_reading_service;

import dk.via.group1.urbanmicrofarm_backend.application.domain.SensorReading;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.google.type.DateTime;

public interface SensorReadingQueryService {
    Optional<SensorReading> getLatestReading(Integer sensorId);
    Optional<SensorReading> getLatestReading(Long sensorId);
    List<SensorReading> getHistoricalReadings(Integer sensorId, Instant from, Instant to);
}
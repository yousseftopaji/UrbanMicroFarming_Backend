package dk.via.group1.urbanmicrofarm_backend.application.domain;

import java.time.Instant;

public class SensorReading {

    private long id;
    private final double value;
    private final Instant timestamp;
    private final long sensorId;

    public SensorReading(long id, double value, Instant timestamp, long sensorId) {
        this.id = id;
        this.value = value;
        this.timestamp = timestamp;
        this.sensorId = sensorId;
    }

    public SensorReading(double value, Instant timestamp, long sensorId) {
        this.value = value;
        this.timestamp = timestamp;
        this.sensorId = sensorId;
    }

    public long getId() { return id; }

    public double getValue() { return value; }

    public Instant getTimestamp() { return timestamp; }

    public long getSensorId() { return sensorId; }
}

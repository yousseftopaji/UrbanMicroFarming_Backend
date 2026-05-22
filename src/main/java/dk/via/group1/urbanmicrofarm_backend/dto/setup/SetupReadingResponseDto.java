package dk.via.group1.urbanmicrofarm_backend.dto.setup;

import java.time.Instant;

public class SetupReadingResponseDto {
    private int setupId;
    private String timestamp;
    private Double temperature;
    private Double humidity;
    private Double light;

    public SetupReadingResponseDto(int setupId, Instant timestamp, Double temperature, Double humidity, Double light) {
        this.setupId = setupId;
        this.timestamp = timestamp != null ? timestamp.toString() : null;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
    }

    public int getSetupId() { return setupId; }
    public void setSetupId(int setupId) { this.setupId = setupId; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public Double getLight() { return light; }
    public void setLight(Double light) { this.light = light; }
}

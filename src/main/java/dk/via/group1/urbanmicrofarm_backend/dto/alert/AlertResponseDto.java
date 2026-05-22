package dk.via.group1.urbanmicrofarm_backend.dto.alert;

import java.time.Instant;

public class AlertResponseDto {
    private Long id;
    private String type;
    private String severity;
    private String message;
    private Long setupId;
    private Long plantId;
    private Long sensorId;
    private String status;
    private String triggeredAt;

    public AlertResponseDto() {}

    public AlertResponseDto(Long id, String type, String severity, String message,
                            Long setupId, Long plantId, Long sensorId, String status, Instant triggeredAt) {
        this.id = id;
        this.type = type;
        this.severity = severity;
        this.message = message;
        this.setupId = setupId;
        this.plantId = plantId;
        this.sensorId = sensorId;
        this.status = status;
        this.triggeredAt = triggeredAt != null ? triggeredAt.toString() : null;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getSetupId() { return setupId; }
    public void setSetupId(Long setupId) { this.setupId = setupId; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public Long getSensorId() { return sensorId; }
    public void setSensorId(Long sensorId) { this.sensorId = sensorId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTriggeredAt() { return triggeredAt; }
    public void setTriggeredAt(String triggeredAt) { this.triggeredAt = triggeredAt; }
}

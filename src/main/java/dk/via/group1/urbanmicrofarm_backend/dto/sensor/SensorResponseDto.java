package dk.via.group1.urbanmicrofarm_backend.dto.sensor;

public class SensorResponseDto {
    private Long id;
    private String sensorType;
    private String status;

    public SensorResponseDto(Long id, String sensorType, String status) {
        this.id = id;
        this.sensorType = sensorType;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

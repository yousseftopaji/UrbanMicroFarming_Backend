package dk.via.group1.urbanmicrofarm_backend.dto.sensor;

public class SensorResponseDto {
    private Long id;
    private String status;

    public SensorResponseDto(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

package dk.via.group1.urbanmicrofarm_backend.database.entities;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "alert", schema = "urban_micro_farm_app")
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    private String message;
    private String status;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = true)
    private Long sensorReadingId;

    @OneToOne
    @JoinColumn(name = "sensorReadingId", referencedColumnName = "id", insertable = false, updatable = false)
    private SensorReadingEntity sensorReading;

    @Column(nullable = true)
    private Long watering_event_id;

    @OneToOne
    @JoinColumn(name = "watering_event_id", referencedColumnName = "id", insertable = false, updatable = false)
    private WateringEventEntity wateringEvent;

    @Column(nullable = true)
    private Long userId;

    @Column(nullable = true)
    private String severity;

    @Column(nullable = true)
    private Long setupId;

    @Column(nullable = true)
    private Long plantId;

    @Column(nullable = true)
    private Long sensorId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public Long getSensorReadingId() { return sensorReadingId; }
    public void setSensorReadingId(Long sensorReadingId) { this.sensorReadingId = sensorReadingId; }

    public SensorReadingEntity getSensorReading() { return sensorReading; }
    public void setSensorReading(SensorReadingEntity sensorReading) { this.sensorReading = sensorReading; }

    public Long getWateringEventId() { return watering_event_id; }
    public void setWateringEventId(Long wateringEventId) { this.watering_event_id = wateringEventId; }

    public WateringEventEntity getWateringEvent() { return wateringEvent; }
    public void setWateringEvent(WateringEventEntity wateringEvent) { this.wateringEvent = wateringEvent; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public Long getSetupId() { return setupId; }
    public void setSetupId(Long setupId) { this.setupId = setupId; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public Long getSensorId() { return sensorId; }
    public void setSensorId(Long sensorId) { this.sensorId = sensorId; }
}

package dk.via.group1.urbanmicrofarm_backend.database.entities;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sensor_readings", schema = "urban_micro_farm_app")
public class SensorReadingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private Long sensor_id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SensorEntity sensor;

    @OneToOne(mappedBy = "sensorReading")
    private AlertEntity alert;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public Long getSensorId() { return sensor_id; }
    public void setSensorId(Long sensorId) { this.sensor_id = sensorId; }

    public SensorEntity getSensor() { return sensor; }
    public void setSensor(SensorEntity sensor) { this.sensor = sensor; }

    public AlertEntity getAlert() { return alert; }
    public void setAlert(AlertEntity alert) { this.alert = alert; }
}

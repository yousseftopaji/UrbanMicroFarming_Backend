package dk.via.group1.urbanmicrofarm_backend.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor", schema = "urban_micro_farm_app")
public class SensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private int setup_id;

    @ManyToOne
    @JoinColumn(name = "setup_id", referencedColumnName = "setup_id", insertable = false, updatable = false)
    private GrowingSetupEntity growingSetup;

    @Column(nullable = false)
    private String sensor_type_name;

    @ManyToOne
    @JoinColumn(name = "sensor_type_name", referencedColumnName = "name", insertable = false, updatable = false)
    private SensorTypeEntity sensorType;

    @OneToMany(mappedBy = "sensor")
    private java.util.List<SensorReadingEntity> sensorReadings;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public int getSetupId() { return setup_id; }
    public void setSetupId(int setupId) { this.setup_id = setupId; }

    public GrowingSetupEntity getGrowingSetup() { return growingSetup; }
    public void setGrowingSetup(GrowingSetupEntity growingSetup) { this.growingSetup = growingSetup; }

    public String getSensorTypeName() { return sensor_type_name; }
    public void setSensorTypeName(String sensorTypeName) { this.sensor_type_name = sensorTypeName; }

    public SensorTypeEntity getSensorType() { return sensorType; }
    public void setSensorType(SensorTypeEntity sensorType) { this.sensorType = sensorType; }
}

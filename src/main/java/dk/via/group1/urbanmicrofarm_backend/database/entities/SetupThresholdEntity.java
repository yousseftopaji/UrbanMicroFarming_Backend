package dk.via.group1.urbanmicrofarm_backend.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "setup_threshold", schema = "urban_micro_farm_app")
public class SetupThresholdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setup_id", nullable = false, unique = true)
    private int setupId;

    @Column(nullable = false)
    private double moistureMin = 200.0;

    @Column(nullable = false)
    private double waterConsumptionMaxLiters = 10.0;

    @Column(nullable = false)
    private double baselineWaterLiters = 0.0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getSetupId() { return setupId; }
    public void setSetupId(int setupId) { this.setupId = setupId; }

    public double getMoistureMin() { return moistureMin; }
    public void setMoistureMin(double moistureMin) { this.moistureMin = moistureMin; }

    public double getWaterConsumptionMaxLiters() { return waterConsumptionMaxLiters; }
    public void setWaterConsumptionMaxLiters(double waterConsumptionMaxLiters) { this.waterConsumptionMaxLiters = waterConsumptionMaxLiters; }

    public double getBaselineWaterLiters() { return baselineWaterLiters; }
    public void setBaselineWaterLiters(double baselineWaterLiters) { this.baselineWaterLiters = baselineWaterLiters; }
}

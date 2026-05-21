package dk.via.group1.urbanmicrofarm_backend.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "threshold", schema = "urban_micro_farm_app")
public class ThresholdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Long plant_id;

    @OneToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PlantEntity plant;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public Long getPlantId() { return plant_id; }
    public void setPlantId(Long plantId) { this.plant_id = plantId; }

    public PlantEntity getPlant() { return plant; }
    public void setPlant(PlantEntity plant) { this.plant = plant; }
}

package dk.via.group1.urbanmicrofarm_backend.dto.threshold;

public class UpdateThresholdsRequest {
    private Double moistureMin;
    private Double waterConsumptionMaxLiters;

    public Double getMoistureMin() { return moistureMin; }
    public void setMoistureMin(Double moistureMin) { this.moistureMin = moistureMin; }

    public Double getWaterConsumptionMaxLiters() { return waterConsumptionMaxLiters; }
    public void setWaterConsumptionMaxLiters(Double waterConsumptionMaxLiters) { this.waterConsumptionMaxLiters = waterConsumptionMaxLiters; }
}

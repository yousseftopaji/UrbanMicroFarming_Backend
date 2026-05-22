package dk.via.group1.urbanmicrofarm_backend.dto.threshold;

public class SetupThresholdResponseDto {
    private int setupId;
    private double moistureMin;
    private double waterConsumptionMaxLiters;
    private double baselineWaterLiters;

    public SetupThresholdResponseDto(int setupId, double moistureMin, double waterConsumptionMaxLiters, double baselineWaterLiters) {
        this.setupId = setupId;
        this.moistureMin = moistureMin;
        this.waterConsumptionMaxLiters = waterConsumptionMaxLiters;
        this.baselineWaterLiters = baselineWaterLiters;
    }

    public int getSetupId() { return setupId; }
    public void setSetupId(int setupId) { this.setupId = setupId; }

    public double getMoistureMin() { return moistureMin; }
    public void setMoistureMin(double moistureMin) { this.moistureMin = moistureMin; }

    public double getWaterConsumptionMaxLiters() { return waterConsumptionMaxLiters; }
    public void setWaterConsumptionMaxLiters(double waterConsumptionMaxLiters) { this.waterConsumptionMaxLiters = waterConsumptionMaxLiters; }

    public double getBaselineWaterLiters() { return baselineWaterLiters; }
    public void setBaselineWaterLiters(double baselineWaterLiters) { this.baselineWaterLiters = baselineWaterLiters; }
}

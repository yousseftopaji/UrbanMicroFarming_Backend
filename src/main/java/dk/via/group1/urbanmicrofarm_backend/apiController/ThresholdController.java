package dk.via.group1.urbanmicrofarm_backend.apiController;

import dk.via.group1.urbanmicrofarm_backend.database.entities.SetupThresholdEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SetupThresholdRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.threshold.SetupThresholdResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.threshold.UpdateThresholdsRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/growingsetups")
public class ThresholdController {

    private final SetupThresholdRepository thresholdRepository;

    public ThresholdController(SetupThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    @GetMapping("/{setupId}/thresholds")
    public SetupThresholdResponseDto getThresholds(@PathVariable int setupId) {
        SetupThresholdEntity entity = thresholdRepository.findBySetupId(setupId)
                .orElseGet(() -> createDefault(setupId));
        return toDto(entity);
    }

    @PatchMapping("/{setupId}/thresholds")
    public SetupThresholdResponseDto updateThresholds(
            @PathVariable int setupId,
            @RequestBody UpdateThresholdsRequest request) {

        SetupThresholdEntity entity = thresholdRepository.findBySetupId(setupId)
                .orElseGet(() -> createDefault(setupId));

        if (request.getMoistureMin() != null) {
            entity.setMoistureMin(request.getMoistureMin());
        }
        if (request.getWaterConsumptionMaxLiters() != null) {
            entity.setWaterConsumptionMaxLiters(request.getWaterConsumptionMaxLiters());
        }

        thresholdRepository.save(entity);
        return toDto(entity);
    }

    private SetupThresholdEntity createDefault(int setupId) {
        SetupThresholdEntity entity = new SetupThresholdEntity();
        entity.setSetupId(setupId);
        entity.setMoistureMin(200.0);
        entity.setWaterConsumptionMaxLiters(10.0);
        entity.setBaselineWaterLiters(0.0);
        return thresholdRepository.save(entity);
    }

    private SetupThresholdResponseDto toDto(SetupThresholdEntity entity) {
        return new SetupThresholdResponseDto(
                entity.getSetupId(),
                entity.getMoistureMin(),
                entity.getWaterConsumptionMaxLiters(),
                entity.getBaselineWaterLiters()
        );
    }
}

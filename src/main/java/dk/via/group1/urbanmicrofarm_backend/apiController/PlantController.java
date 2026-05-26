package dk.via.group1.urbanmicrofarm_backend.apiController;

import dk.via.group1.urbanmicrofarm_backend.application.services.plant_service.PlantService;
import dk.via.group1.urbanmicrofarm_backend.application.services.watering.WateringService;
import dk.via.group1.urbanmicrofarm_backend.database.entities.PlantEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.PredictionRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SensorReadingRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SetupThresholdRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.CreatePlantRequestDto;
import dk.via.group1.urbanmicrofarm_backend.dto.MessageResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.PlantResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.PredictionResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.UpdatePlantRequestDto;
import dk.via.group1.urbanmicrofarm_backend.mapper.apiMapper.PlantApiMapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlantController {

    private final PlantService plantService;
    private final PlantApiMapper plantApiMapper;
    private final WateringService wateringService;
    private final PredictionRepository predictionRepository;
    private final SensorReadingRepository sensorReadingRepository;
    private final SetupThresholdRepository setupThresholdRepository;

    public PlantController(PlantService plantService, PlantApiMapper plantApiMapper, WateringService wateringService,
                           PredictionRepository predictionRepository, SensorReadingRepository sensorReadingRepository,
                           SetupThresholdRepository setupThresholdRepository) {
        this.plantService = plantService;
        this.plantApiMapper = plantApiMapper;
        this.wateringService = wateringService;
        this.predictionRepository = predictionRepository;
        this.sensorReadingRepository = sensorReadingRepository;
        this.setupThresholdRepository = setupThresholdRepository;
    }

    @GetMapping("/growingsetups/{setupId}/plants")
    public List<PlantResponseDto> getPlantsBySetup(@PathVariable Long setupId) {
        double moistureMin = setupThresholdRepository.findBySetupId(setupId.intValue())
                .map(t -> t.getMoistureMin())
                .orElse(200.0);

        return plantService.getPlantsBySetup(setupId)
                .stream()
                .map(plant -> {
                    PlantResponseDto dto = plantApiMapper.toResponseDto(plant);
                    sensorReadingRepository
                            .findFirstBySensorIdOrderByTimestampDesc(plant.getSensorId().intValue())
                            .ifPresentOrElse(
                                    r -> dto.setHealth(r.getValue() < moistureMin ? "stressed" : "healthy"),
                                    () -> dto.setHealth("unknown")
                            );
                    return dto;
                })
                .toList();
    }

    @GetMapping("/plants/{plantId}")
    public PlantResponseDto getPlant(@PathVariable Long plantId) {
        PlantEntity plantEntity = plantService.getPlant(plantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));

        PlantResponseDto dto = plantApiMapper.toResponseDto(plantEntity);

        int setupId = plantEntity.getSensor().getSetupId();
        double moistureMin = setupThresholdRepository.findBySetupId(setupId)
                .map(t -> t.getMoistureMin())
                .orElse(200.0);

        sensorReadingRepository
                .findFirstBySensorIdOrderByTimestampDesc(plantEntity.getSensorId().intValue())
                .ifPresentOrElse(
                        r -> dto.setHealth(r.getValue() < moistureMin ? "stressed" : "healthy"),
                        () -> dto.setHealth("unknown")
                );

        return dto;
    }

    @GetMapping("/sensors/{sensorId}/plant")
    public PlantResponseDto getPlantBySensor(@PathVariable Long sensorId) {
        PlantEntity plantEntity = plantService.getPlantBySensor(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return plantApiMapper.toResponseDto(plantEntity);
    }

    @PostMapping("/plants")
    @ResponseStatus(HttpStatus.CREATED)
    public PlantResponseDto addPlant(@RequestBody CreatePlantRequestDto dto) {
        PlantEntity plantEntity = plantApiMapper.fromCreateRequestDto(dto);
        return plantApiMapper.toResponseDto(plantService.addPlant(plantEntity));
    }

    @PatchMapping("/plants/{plantId}")
    public PlantResponseDto updatePlant(@PathVariable Long plantId, @RequestBody UpdatePlantRequestDto dto) {
        PlantEntity updatedPlant = plantApiMapper.fromUpdateRequestDto(dto);
        return plantApiMapper.toResponseDto(plantService.updatePlant(plantId, updatedPlant));
    }

    @DeleteMapping("/plants/{plantId}")
    public MessageResponseDto removePlant(@PathVariable Long plantId) {
        plantService.removePlant(plantId);
        return new MessageResponseDto("Plant removed successfully");
    }

    @PutMapping("/plants/{plantId}/photo")
    public PlantResponseDto updatePlantPhoto(@PathVariable Long plantId, @RequestBody java.util.Map<String, String> body) {
        return plantApiMapper.toResponseDto(plantService.updatePhoto(plantId, body.get("photo")));
    }

    @PostMapping("/plants/{plantId}/watering/manual")
    public MessageResponseDto triggerManualWatering(@PathVariable Long plantId) {
        wateringService.triggerManualWatering(plantId);
        return new MessageResponseDto("Watering triggered");
    }

    @GetMapping("/plants/{plantId}/predictions")
    public List<PredictionResponseDto> getPredictions(@PathVariable Long plantId) {
        PlantEntity plant = plantService.getPlant(plantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return predictionRepository.findByPlantId(plantId).stream()
                .map(p -> new PredictionResponseDto(p.getId(), p.getPredictedValue(), p.getCreatedAt(), plant.getName()))
                .toList();
    }
}

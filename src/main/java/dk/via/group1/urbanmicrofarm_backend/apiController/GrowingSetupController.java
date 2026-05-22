package dk.via.group1.urbanmicrofarm_backend.apiController;

import dk.via.group1.urbanmicrofarm_backend.application.domain.GrowingSetup;
import dk.via.group1.urbanmicrofarm_backend.application.services.growing_setup_service.GrowingSetupService;
import dk.via.group1.urbanmicrofarm_backend.application.services.watering.WateringService;
import dk.via.group1.urbanmicrofarm_backend.database.entities.SensorEntity;
import dk.via.group1.urbanmicrofarm_backend.database.entities.SensorReadingEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SensorReadingRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.SensorRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.MessageResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.WateringEventResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.growingSetup.GrowingSetupAssignDto;
import dk.via.group1.urbanmicrofarm_backend.dto.growingSetup.GrowingSetupResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.growingSetup.PatchLocationDto;
import dk.via.group1.urbanmicrofarm_backend.dto.sensor.SensorResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.setup.SetupReadingResponseDto;
import dk.via.group1.urbanmicrofarm_backend.mapper.apiMapper.GrowingSetupApiMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/growingsetups")
public class GrowingSetupController {

  private final GrowingSetupService service;
  private final GrowingSetupApiMapper apiMapper;
  private final WateringService wateringService;
  private final SensorRepository sensorRepository;
  private final SensorReadingRepository sensorReadingRepository;

  public GrowingSetupController(GrowingSetupService service, GrowingSetupApiMapper apiMapper,
                                 WateringService wateringService, SensorRepository sensorRepository,
                                 SensorReadingRepository sensorReadingRepository) {
    this.service = service;
    this.apiMapper = apiMapper;
    this.wateringService = wateringService;
    this.sensorRepository = sensorRepository;
    this.sensorReadingRepository = sensorReadingRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public GrowingSetupResponseDto assignSetupToUser(@RequestBody GrowingSetupAssignDto request) {
    GrowingSetup assigned = service.assignSetupToUser(request.getUserId(), request.getSetupId());
    return apiMapper.toResponseDto(assigned);
  }

  @PatchMapping("/{setupId}")
  @ResponseStatus(HttpStatus.OK)
  public GrowingSetupResponseDto updateLocation(
      @PathVariable int setupId,
      @RequestBody PatchLocationDto patchLocationDto) {
    GrowingSetup updated = service.updateSetupLocation(setupId, patchLocationDto.getLocation());
    return apiMapper.toResponseDto(updated);
  }

  @DeleteMapping("/{setupId}")
  @ResponseStatus(HttpStatus.OK)
  public MessageResponseDto disconnectSetup(@PathVariable int setupId) {
    service.disconnectSetup(setupId);
    return new MessageResponseDto("Setup disconnected successfully");
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<GrowingSetupResponseDto.GrowingSetupDetails> getSetupsForUser(
      @RequestParam("userId") int userId) {

    return service.getSetupsForUser(userId)
        .stream()
        .map(setup -> apiMapper.toResponseDto(setup).getGrowingSetup())
        .collect(Collectors.toList());
  }

  @GetMapping("/{setupId}/wateringEvents/latest")
  public WateringEventResponseDto getLatestWateringEvent(@PathVariable int setupId) {
    return wateringService.getLatestWateringEvent(setupId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No watering events found for setup " + setupId));
  }

  @GetMapping("/{setupId}/wateringEvents")
  public List<WateringEventResponseDto> getWateringEventHistory(
      @PathVariable int setupId,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {
    return wateringService.getWateringEventHistory(setupId, from, to);
  }

  @GetMapping("/{setupId}/sensors")
  public List<SensorResponseDto> getSensorsForSetup(@PathVariable int setupId) {
    java.time.Instant oneHourAgo = java.time.Instant.now().minusSeconds(3600);
    return sensorRepository.findBySetupId(setupId)
        .stream()
        .map(s -> {
          boolean hasRecentReading = sensorReadingRepository
              .findFirstBySensorIdOrderByTimestampDesc(s.getId().intValue())
              .map(r -> r.getTimestamp().isAfter(oneHourAgo))
              .orElse(false);
          return new SensorResponseDto(s.getId(), hasRecentReading ? "Active" : "No data");
        })
        .collect(Collectors.toList());
  }

  @GetMapping("/{setupId}/readings/latest")
  public SetupReadingResponseDto getLatestReadings(@PathVariable int setupId) {
    List<SensorEntity> sensors = sensorRepository.findBySetupId(setupId);

    Double temperature = null;
    Double humidity = null;
    Double light = null;
    Instant latestTimestamp = null;

    for (SensorEntity sensor : sensors) {
      Optional<SensorReadingEntity> readingOpt =
          sensorReadingRepository.findFirstBySensorIdOrderByTimestampDesc(sensor.getId().intValue());
      if (readingOpt.isPresent()) {
        SensorReadingEntity reading = readingOpt.get();
        if (latestTimestamp == null || reading.getTimestamp().isAfter(latestTimestamp)) {
          latestTimestamp = reading.getTimestamp();
        }
        String typeName = sensor.getSensorTypeName();
        if ("Temperature".equals(typeName)) temperature = reading.getValue();
        else if ("Humidity".equals(typeName)) humidity = reading.getValue();
        else if ("Light".equals(typeName)) light = reading.getValue();
      }
    }

    return new SetupReadingResponseDto(setupId, latestTimestamp, temperature, humidity, light);
  }
}
package dk.via.group1.urbanmicrofarm_backend.application.services.watering;

import dk.via.group1.urbanmicrofarm_backend.dto.WateringEventResponseDto;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface WateringService {
    void triggerManualWatering(Long plantId);
    Optional<WateringEventResponseDto> getLatestWateringEvent(int setupId);
    List<WateringEventResponseDto> getWateringEventHistory(int setupId, Instant from, Instant to);
}

package dk.via.group1.urbanmicrofarm_backend.dto;

import java.time.Instant;

public record WateringEventResponseDto(
        Long id,
        Double waterUsedMl,
        String mode,
        Instant createdAt
) {}

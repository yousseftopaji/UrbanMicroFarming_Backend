package dk.via.group1.urbanmicrofarm_backend.dto;

import java.time.Instant;

public record PredictionResponseDto(
        Long predictionId,
        Double predictedValue,
        Instant createdAt,
        String plantName
) {
}

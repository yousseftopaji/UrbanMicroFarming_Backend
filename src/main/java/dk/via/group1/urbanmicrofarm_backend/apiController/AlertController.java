package dk.via.group1.urbanmicrofarm_backend.apiController;

import dk.via.group1.urbanmicrofarm_backend.database.entities.AlertEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.AlertRepository;
import dk.via.group1.urbanmicrofarm_backend.dto.alert.AlertResponseDto;
import dk.via.group1.urbanmicrofarm_backend.dto.alert.UpdateAlertStatusRequest;
import dk.via.group1.urbanmicrofarm_backend.dto.alert.UpdateAlertStatusResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AlertController {

    private final AlertRepository alertRepository;

    public AlertController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @GetMapping("/api/users/{userId}/alerts")
    public List<AlertResponseDto> getAlerts(
            @PathVariable Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long setupId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {

        Instant effectiveFrom = from != null ? from : Instant.EPOCH;
        Instant effectiveTo = to != null ? to : Instant.ofEpochSecond(253402300799L);
        List<AlertEntity> alerts = alertRepository.findByUserIdWithFilters(userId, status, setupId, effectiveFrom, effectiveTo);
        return alerts.stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/alerts/{alertId}")
    public AlertResponseDto getAlert(@PathVariable Long alertId) {
        AlertEntity alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found"));
        return toDto(alert);
    }

    @PatchMapping("/api/alerts/{alertId}/status")
    public UpdateAlertStatusResponse updateAlertStatus(
            @PathVariable Long alertId,
            @RequestBody UpdateAlertStatusRequest request) {

        AlertEntity alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found"));

        alert.setStatus(request.getStatus());
        alertRepository.save(alert);

        return new UpdateAlertStatusResponse(alert.getId(), alert.getStatus());
    }

    private AlertResponseDto toDto(AlertEntity alert) {
        return new AlertResponseDto(
                alert.getId(),
                alert.getType(),
                alert.getSeverity(),
                alert.getMessage(),
                alert.getSetupId(),
                alert.getPlantId(),
                alert.getSensorId(),
                alert.getStatus(),
                alert.getTimestamp()
        );
    }
}

package dk.via.group1.urbanmicrofarm_backend.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateNameRequest(
    @NotBlank(message = "Name is required") String name
) {}

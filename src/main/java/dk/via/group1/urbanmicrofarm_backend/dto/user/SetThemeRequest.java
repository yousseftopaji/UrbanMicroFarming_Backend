package dk.via.group1.urbanmicrofarm_backend.dto.user;

import dk.via.group1.urbanmicrofarm_backend.model.Theme;
import jakarta.validation.constraints.NotNull;

public record SetThemeRequest(
    @NotNull(message = "Theme is required") Theme theme
) {}

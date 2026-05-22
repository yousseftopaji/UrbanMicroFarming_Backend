package dk.via.group1.urbanmicrofarm_backend.user.mapper;

import dk.via.group1.urbanmicrofarm_backend.database.entities.UserEntity;
import dk.via.group1.urbanmicrofarm_backend.user.dto.RegisterRequest;
import dk.via.group1.urbanmicrofarm_backend.user.dto.UserDto;
import dk.via.group1.urbanmicrofarm_backend.user.model.Theme;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity toEntity(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setTheme(Theme.SYSTEM);
        return user;
    }

    public UserDto toDto(UserEntity user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), lower(user.getTheme()));
    }

    public UserDto toLoginDto(UserEntity user) {
        return new UserDto(user.getId(), null, user.getEmail(), lower(user.getTheme()));
    }

    public UserDto toNameDto(UserEntity user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), null);
    }

    public UserDto toEmailDto(UserEntity user) {
        return new UserDto(user.getId(), null, user.getEmail(), null);
    }

    public UserDto toThemeDto(UserEntity user) {
        return new UserDto(user.getId(), null, user.getEmail(), lower(user.getTheme()));
    }

    private String lower(Theme theme) {
        return theme.name().toLowerCase();
    }
}

package dk.via.group1.urbanmicrofarm_backend.user.mapper;

import dk.via.group1.urbanmicrofarm_backend.database.entities.UserEntity;
import dk.via.group1.urbanmicrofarm_backend.dto.user.RegisterRequest;
import dk.via.group1.urbanmicrofarm_backend.dto.user.UserDto;
import dk.via.group1.urbanmicrofarm_backend.mapper.apiMapper.UserMapper;
import dk.via.group1.urbanmicrofarm_backend.model.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private PasswordEncoder passwordEncoder;
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder(4);
        mapper = new UserMapper(passwordEncoder);
    }

    // ── toEntity ──────────────────────────────────────────────

    @Test
    void toEntity_hashesPasswordSecurely() {
        UserEntity user = mapper.toEntity(new RegisterRequest("Alice", "alice@example.com", "password123"));
        assertThat(user.getPasswordHash()).isNotEqualTo("password123");
        assertThat(passwordEncoder.matches("password123", user.getPasswordHash())).isTrue();
    }

    @Test
    void toEntity_neverStoresRawPassword() {
        UserEntity user = mapper.toEntity(new RegisterRequest("Alice", "alice@example.com", "secret"));
        assertThat(user.getPasswordHash()).doesNotContain("secret");
    }

    @Test
    void toEntity_setsNameAndEmail() {
        UserEntity user = mapper.toEntity(new RegisterRequest("Bob", "bob@example.com", "pass12345"));
        assertThat(user.getName()).isEqualTo("Bob");
        assertThat(user.getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    void toEntity_defaultsThemeToSystem() {
        UserEntity user = mapper.toEntity(new RegisterRequest("Alice", "a@b.com", "password123"));
        assertThat(user.getTheme()).isEqualTo(Theme.SYSTEM);
    }

    // ── toDto ─────────────────────────────────────────────────

    @Test
    void toDto_includesAllFields() {
        UserDto dto = mapper.toDto(buildUser());
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Alice");
        assertThat(dto.email()).isEqualTo("alice@example.com");
        assertThat(dto.theme()).isEqualTo("system");
    }

    @Test
    void toDto_themeSerializedLowercase() {
        UserEntity user = buildUser();
        user.setTheme(Theme.DARK);
        assertThat(mapper.toDto(user).theme()).isEqualTo("dark");
    }

    // ── toLoginDto ────────────────────────────────────────────

    @Test
    void toLoginDto_excludesName() {
        UserDto dto = mapper.toLoginDto(buildUser());
        assertThat(dto.name()).isNull();
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.email()).isEqualTo("alice@example.com");
        assertThat(dto.theme()).isEqualTo("system");
    }

    // ── toNameDto ─────────────────────────────────────────────

    @Test
    void toNameDto_excludesTheme() {
        UserDto dto = mapper.toNameDto(buildUser());
        assertThat(dto.theme()).isNull();
        assertThat(dto.name()).isEqualTo("Alice");
        assertThat(dto.email()).isEqualTo("alice@example.com");
        assertThat(dto.id()).isEqualTo(1L);
    }

    // ── toEmailDto ────────────────────────────────────────────

    @Test
    void toEmailDto_includesOnlyIdAndEmail() {
        UserDto dto = mapper.toEmailDto(buildUser());
        assertThat(dto.name()).isNull();
        assertThat(dto.theme()).isNull();
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.email()).isEqualTo("alice@example.com");
    }

    // ── toThemeDto ────────────────────────────────────────────

    @Test
    void toThemeDto_excludesName() {
        UserDto dto = mapper.toThemeDto(buildUser());
        assertThat(dto.name()).isNull();
        assertThat(dto.theme()).isEqualTo("system");
        assertThat(dto.email()).isEqualTo("alice@example.com");
    }

    // ── passwordHash never leaks through any DTO ──────────────

    @Test
    void noDtoMethod_exposesPasswordHash() {
        UserEntity user = buildUser();
        UserDto full = mapper.toDto(user);
        assertThat(full.toString()).doesNotContain(user.getPasswordHash());
    }

    private UserEntity buildUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPasswordHash(passwordEncoder.encode("secret"));
        user.setTheme(Theme.SYSTEM);
        return user;
    }
}

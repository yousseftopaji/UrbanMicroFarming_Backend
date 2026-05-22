package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.WateringEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface WateringEventRepository extends JpaRepository<WateringEventEntity, Long> {
    Optional<WateringEventEntity> findTopByActuator_SetupIdOrderByCreatedAtDesc(int setupId);
    List<WateringEventEntity> findByActuator_SetupIdAndCreatedAtBetween(int setupId, Instant from, Instant to);
}

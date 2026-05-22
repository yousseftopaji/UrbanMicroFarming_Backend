package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.SetupThresholdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetupThresholdRepository extends JpaRepository<SetupThresholdEntity, Long> {
    Optional<SetupThresholdEntity> findBySetupId(int setupId);
}

package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
    Optional<PlantEntity> findBySensorId(long sensorId);
}

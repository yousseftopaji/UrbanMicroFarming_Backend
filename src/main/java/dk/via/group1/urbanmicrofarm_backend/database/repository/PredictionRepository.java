package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.PredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<PredictionEntity, Long> {
}

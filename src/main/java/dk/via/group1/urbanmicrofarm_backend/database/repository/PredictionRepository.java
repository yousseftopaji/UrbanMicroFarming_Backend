package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.PredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<PredictionEntity, Long> {
    List<PredictionEntity> findByPlantId(Long plantId);
}

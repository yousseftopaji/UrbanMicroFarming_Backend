package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<PlantEntity, String> {
//  List<PlantEntity> findBySetupId(long setupId);
  Optional<PlantEntity> findBySensorId(long sensorId);
  Optional<PlantEntity> findById(long plantId);
  void deleteById(long plantId);
}
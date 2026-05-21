package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.WateringEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringEventRepository extends JpaRepository<WateringEventEntity, Long> {
}

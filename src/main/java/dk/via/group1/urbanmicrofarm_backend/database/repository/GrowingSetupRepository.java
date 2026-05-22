package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.GrowingSetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrowingSetupRepository extends JpaRepository<GrowingSetupEntity, Integer> {
    List<GrowingSetupEntity> findByUser_Id(Long userId);
    Optional<GrowingSetupEntity> findBySerialNumber(String serialNumber);
}

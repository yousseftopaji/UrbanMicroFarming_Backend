package dk.via.group1.urbanmicrofarm_backend.database.repository;

import dk.via.group1.urbanmicrofarm_backend.database.entities.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AlertRepository extends JpaRepository<AlertEntity, Long> {

    List<AlertEntity> findByUserIdOrderByTimestampDesc(Long userId);

    @Query("SELECT a FROM AlertEntity a WHERE a.userId = :userId " +
           "AND a.timestamp >= :from AND a.timestamp <= :to " +
           "ORDER BY a.timestamp DESC")
    List<AlertEntity> findByUserIdAndTimeRange(
            @Param("userId") Long userId,
            @Param("from") Instant from,
            @Param("to") Instant to);
}

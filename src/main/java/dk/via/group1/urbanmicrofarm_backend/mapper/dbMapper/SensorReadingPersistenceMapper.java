package dk.via.group1.urbanmicrofarm_backend.mapper.dbMapper;


import dk.via.group1.urbanmicrofarm_backend.application.domain.Sensor;
import dk.via.group1.urbanmicrofarm_backend.application.domain.SensorReading;
import dk.via.group1.urbanmicrofarm_backend.application.domain.SensorType;
import dk.via.group1.urbanmicrofarm_backend.database.entities.SensorReadingEntity;
import org.springframework.stereotype.Component;

@Component
public class SensorReadingPersistenceMapper {

    public SensorReadingEntity toEntity(SensorReading sensorReading) {
        SensorReadingEntity entity = new SensorReadingEntity();
        entity.setValue(sensorReading.getValue());
        entity.setTimestamp(sensorReading.getTimestamp());
        entity.setSensorId(sensorReading.getSensorId());
        return entity;
    }

    public SensorReading toDomain(SensorReadingEntity entity) {
        return new SensorReading(
                entity.getId(),
                entity.getValue(),
                entity.getTimestamp(),
                entity.getSensorId()
        );
    }
}

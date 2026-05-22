package dk.via.group1.urbanmicrofarm_backend.mapper.dbMapper;

import dk.via.group1.urbanmicrofarm_backend.database.entities.WateringEventEntity;
import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class WateringEventDbMapper {

    public WateringEventEntity toAutomaticEntity(
            WaterPredictionResponseDto dto,
            Long actuatorId
    ) {
        if (dto == null) {
            return null;
        }

        WateringEventEntity entity = new WateringEventEntity();

        entity.setWaterUsed_mL((double) dto.wateringAmount());
        entity.setMode("AUTOMATIC");
        entity.setActuatorId(actuatorId);

        return entity;
    }

    public WateringEventEntity toManualEntity(
            Double waterUsed_mL,
            Long actuatorId
    ) {
        if (waterUsed_mL == null) {
            return null;
        }

        WateringEventEntity entity = new WateringEventEntity();

        entity.setWaterUsed_mL(waterUsed_mL);
        entity.setMode("MANUAL");
        entity.setActuatorId(actuatorId);

        return entity;
    }
}
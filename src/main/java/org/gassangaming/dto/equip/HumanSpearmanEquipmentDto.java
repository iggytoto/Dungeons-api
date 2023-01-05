package org.gassangaming.dto.equip;

import lombok.*;
import org.gassangaming.model.euqipment.human.HumanSpearmanEquipment;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class HumanSpearmanEquipmentDto extends UnitEquipDto {
    private int doubleEdgePoints;
    private int midRangePoints;

    public static HumanSpearmanEquipmentDto ofDomain(HumanSpearmanEquipment t) {
        final var result = HumanSpearmanEquipmentDto.builder().doubleEdgePoints(t.getDoubleEdgePoints()).midRangePoints(t.getMidRangePoints()).build();
        result.setId(t.getId());
        result.setUnitId(t.getUnitId());
        return result;
    }

    public HumanSpearmanEquipment toDomain() {
        final var result = new HumanSpearmanEquipment();
        result.setId(id);
        result.setUnitId(unitId);
        result.setMidRangePoints(doubleEdgePoints);
        result.setDoubleEdgePoints(midRangePoints);
        return result;
    }
}

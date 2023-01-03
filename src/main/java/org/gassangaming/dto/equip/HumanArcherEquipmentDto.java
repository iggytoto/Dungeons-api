package org.gassangaming.dto.equip;

import lombok.*;
import org.gassangaming.model.euqipment.human.HumanArcherEquipment;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class HumanArcherEquipmentDto extends UnitEquipDto {

    private int midRangePoints;
    private int longRangePoints;
    private boolean fireArrows;
    private boolean poisonArrows;

    public static HumanArcherEquipmentDto ofDomain(HumanArcherEquipment t) {
        final var result = HumanArcherEquipmentDto.builder().midRangePoints(t.getMidRangePoints()).longRangePoints(t.getLongRangePoints()).fireArrows(t.isFireArrows()).poisonArrows(t.isPoisonArrows()).build();
        result.setId(t.getId());
        result.setUnitId(t.getUnitId());
        return result;
    }
}

package org.gassangaming.dto.equip;

import lombok.*;
import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class HumanWarriorEquipmentDto extends UnitEquipDto {
    private int defencePoints;
    private int offencePoints;

    public static HumanWarriorEquipmentDto ofDomain(HumanWarriorEquipment t) {
        final var result = HumanWarriorEquipmentDto.builder().defencePoints(t.getDefencePoints()).offencePoints(t.getOffencePoints()).build();
        result.setId(t.getId());
        result.setUnitId(t.getUnitId());
        return result;
    }

    public HumanWarriorEquipment toDomain() {
        final var result = new HumanWarriorEquipment();
        result.setId(id);
        result.setUnitId(unitId);
        result.setDefencePoints(defencePoints);
        result.setOffencePoints(offencePoints);
        return result;
    }
}

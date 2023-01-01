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
public class HumanWarriorEquipDto extends UnitEquipTableDto {
    private int defencePoints;
    private int offencePoints;


    public static HumanWarriorEquipDto Of(HumanWarriorEquipment t) {
        return HumanWarriorEquipDto.builder().defencePoints(t.getDefencePoints()).offencePoints(t.getOffencePoints()).build();
    }

    public HumanWarriorEquipment toDomain() {
        final var result = new HumanWarriorEquipment();
        result.setDefencePoints(defencePoints);
        result.setOffencePoints(offencePoints);
        return result;
    }
}

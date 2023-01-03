package org.gassangaming.dto.equip;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.euqipment.human.HumanArcherEquipment;
import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;

import java.io.Serializable;


@Getter
@Setter
public abstract class UnitEquipDto implements Serializable {

    protected Long id;
    protected long unitId;

    public static UnitEquipDto of(UnitEquip eq) {
        if (eq instanceof HumanWarriorEquipment) {
            return HumanWarriorEquipmentDto.ofDomain((HumanWarriorEquipment) eq);
        } else if (eq instanceof HumanArcherEquipment) {
            return HumanArcherEquipmentDto.ofDomain((HumanArcherEquipment) eq);
        }
        throw new IllegalStateException();
    }
}

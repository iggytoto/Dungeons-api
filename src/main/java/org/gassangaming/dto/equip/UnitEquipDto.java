package org.gassangaming.dto.equip;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.euqipment.human.HumanArcherEquipment;
import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;


@Getter
@Setter
public abstract class UnitEquipDto extends DtoBase {

    protected Long id;
    protected long unitId;

    public static UnitEquipDto of(UnitEquip eq) {
        if (eq == null) {
            return null;
        }
        if (eq instanceof HumanWarriorEquipment) {
            return HumanWarriorEquipmentDto.ofDomain((HumanWarriorEquipment) eq);
        } else if (eq instanceof HumanArcherEquipment) {
            return HumanArcherEquipmentDto.ofDomain((HumanArcherEquipment) eq);
        }
        throw new IllegalStateException();
    }
}

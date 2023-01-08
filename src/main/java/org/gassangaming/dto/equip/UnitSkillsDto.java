package org.gassangaming.dto.equip;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.model.euqipment.UnitSkills;
import org.gassangaming.model.euqipment.human.HumanArcherSkills;
import org.gassangaming.model.euqipment.human.HumanClericSkills;
import org.gassangaming.model.euqipment.human.HumanSpearmanSkills;
import org.gassangaming.model.euqipment.human.HumanWarriorSkills;


@Getter
@Setter
public abstract class UnitSkillsDto extends DtoBase {

    protected Long id;
    protected long unitId;

    public static UnitSkillsDto of(UnitSkills eq) {
        if (eq == null) {
            return null;
        }
        if (eq instanceof HumanWarriorSkills) {
            return HumanWarriorEquipmentDto.ofDomain((HumanWarriorSkills) eq);
        } else if (eq instanceof HumanArcherSkills) {
            return HumanArcherSkillsDto.ofDomain((HumanArcherSkills) eq);
        } else if (eq instanceof HumanSpearmanSkills) {
            HumanSpearmanEquipmentDto.ofDomain((HumanSpearmanSkills) eq);
        } else if (eq instanceof HumanClericSkills) {
            HumanClericEquipmentDto.ofDomain((HumanClericSkills) eq);
        }
        throw new IllegalStateException();
    }
}

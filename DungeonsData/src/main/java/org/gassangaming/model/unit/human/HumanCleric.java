package org.gassangaming.model.unit.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.skills.human.HumanClericSkills;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("HumanCleric")
public class HumanCleric extends Unit {
    public static final float ATTACK_SPEED_BASE = 1;
    public static final float ATTACK_RANGE_BASE = 10;
    public static final int DAMAGE_BASE = 25;
    public static final int HP_BASE = 25;
    public static final int ARMOR_BASE = 0;
    public static final float MOVE_SPEED_BASE = 4;
    public static final int MR_BASE = 10;
    public static final int MANA_BASE = 150;

    public HumanCleric() {
        hitPoints = HP_BASE;
        maxHitPoints = HP_BASE;
        armor = ARMOR_BASE;
        magicResistance = MR_BASE;
        activity = Activity.Idle;
        battleBehavior = BattleBehavior.StraightAttack;
        attackRange = ATTACK_RANGE_BASE;
        attackSpeed = ATTACK_SPEED_BASE;
        movementSpeed = MOVE_SPEED_BASE;
        damage = DAMAGE_BASE;
        maxMana = MANA_BASE;
        unitType = UnitType.HumanCleric;
        setSkills(new HumanClericSkills());
    }


    public static HumanCleric ofName(String name){
        final var result = new HumanCleric();
        result.name = name;
        return result;
    }
    public static HumanCleric of(Activity activity) {
        final var result = new HumanCleric();
        result.activity = activity;
        return result;
    }

    public static HumanCleric of(long unitId, Activity activity) {
        final var result = HumanCleric.of(activity);
        result.id = unitId;
        return result;
    }
}

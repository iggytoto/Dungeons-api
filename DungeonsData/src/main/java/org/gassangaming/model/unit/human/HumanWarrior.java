package org.gassangaming.model.unit.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.skills.human.HumanWarriorSkills;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("HumanWarrior")
public class HumanWarrior extends Unit {

    public static final float ATTACK_SPEED_BASE = 1;
    public static final float ATTACK_RANGE_BASE = 2;
    public static final int DAMAGE_BASE = 50;
    public static final int HP_BASE = 100;
    public static final int ARMOR_BASE = 15;
    public static final float MOVE_SPEED_BASE = 6;
    public static final int MR_BASE = 0;
    public static final int MANA_BASE = 50;

    public HumanWarrior() {
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
        unitType = UnitType.HumanWarrior;
        setSkills(new HumanWarriorSkills());
    }

    public static HumanWarrior ofName(String name){
        final var result = new HumanWarrior();
        result.name = name;
        return result;
    }

    public static HumanWarrior of(Activity activity) {
        final var result = new HumanWarrior();
        result.activity = activity;
        return result;
    }

    public static HumanWarrior of(long unitId, Activity activity) {
        final var result = HumanWarrior.of(activity);
        result.id = unitId;
        return result;
    }
}

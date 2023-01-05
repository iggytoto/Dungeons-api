package org.gassangaming.model.unit.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("HumanSpearman")
public class HumanSpearman extends Unit {
    public static final float ATTACK_SPEED_BASE = 2;
    public static final float ATTACK_RANGE_BASE = 4;
    public static final int DAMAGE_BASE = 40;
    public static final int HP_BASE = 50;
    public static final int ARMOR_BASE = 10;
    public static final float MOVE_SPEED_BASE = 7;
    public static final int MR_BASE = 0;
    public static final int MANA_BASE = 75;

    public HumanSpearman() {
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
        unitType = UnitType.HumanSpearman;
    }

    public static HumanSpearman ofName(String name){
        final var result = new HumanSpearman();
        result.name = name;
        return result;
    }

    public static HumanSpearman of(Activity activity) {
        final var result = new HumanSpearman();
        result.activity = activity;
        return result;
    }

    public static HumanSpearman of(long unitId, Activity activity) {
        final var result = HumanSpearman.of(activity);
        result.id = unitId;
        return result;
    }
}

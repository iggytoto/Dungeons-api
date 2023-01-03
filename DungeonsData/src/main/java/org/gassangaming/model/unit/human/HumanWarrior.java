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
@DiscriminatorValue("HumanWarrior")
public class HumanWarrior extends Unit {

    public HumanWarrior() {
        hitPoints = 100;
        maxHitPoints = 100;
        armor = 15;
        magicResistance = 0;
        activity = Activity.Idle;
        battleBehavior = BattleBehavior.StraightAttack;
        attackRange = 2;
        attackSpeed = 1;
        movementSpeed = 6;
        damage = 50;
        maxMana = 100;
        unitType = UnitType.HumanWarrior;
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

package org.gassangaming.model.unit.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Random;

@Entity
@Getter
@Setter
@DiscriminatorValue("Dummy")
public class DummyUnit extends Unit {

    public DummyUnit() {
        var rng = new Random();
        hitPoints = rng.nextInt(100, 200);
        maxHitPoints = rng.nextInt(100, 200);
        armor = rng.nextInt(100, 200);
        magicResistance = rng.nextInt(100, 200);
        activity = Activity.Idle;
        battleBehavior = BattleBehavior.StraightAttack;
        attackRange = rng.nextInt(1, 2);
        attackSpeed = rng.nextInt(1, 2);
        movementSpeed = rng.nextInt(3, 6);
        damage = rng.nextInt(10, 100);
        unitType = UnitType.Dummy;
    }
}

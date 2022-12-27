package org.gassangaming.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.gassangaming.model.Activity;
import org.gassangaming.model.BattleBehavior;
import org.gassangaming.model.Unit;
import org.gassangaming.model.UnitType;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnitDto implements Serializable {
    private long id;
    private String name;
    private int hitPoints;
    private int maxHitPoints;
    private int armor;
    private int magicResistance;
    private int damage;
    private float attackSpeed;
    private int trainingExperience;
    private float attackRange;
    private float movementSpeed;
    private Activity activity;
    private BattleBehavior battleBehavior;
    private UnitType unitType;

    public static UnitDto Of(Unit u) {
        return UnitDto
                .builder()
                .id(u.getId())
                .name(u.getName())
                .hitPoints(u.getHitPoints())
                .maxHitPoints(u.getMaxHitPoints())
                .armor(u.getArmor())
                .magicResistance(u.getMagicResistance())
                .damage(u.getDamage())
                .attackSpeed(u.getAttackSpeed())
                .trainingExperience(u.getTrainingExperience())
                .attackRange(u.getAttackRange())
                .movementSpeed(u.getMovementSpeed())
                .activity(u.getActivity())
                .battleBehavior(u.getBattleBehavior())
                .unitType(u.getUnitType())
                .build();
    }

    @JsonIgnore
    public Unit ToDomain() {
        final var result = new Unit();
        result.setId(id);
        result.setName(name);
        result.setHitPoints(hitPoints);
        result.setMaxHitPoints(maxHitPoints);
        result.setArmor(armor);
        result.setMagicResistance(magicResistance);
        result.setDamage(damage);
        result.setAttackSpeed(attackSpeed);
        result.setMovementSpeed(movementSpeed);
        result.setActivity(activity);
        result.setBattleBehavior(battleBehavior);
        result.setUnitType(unitType);
        return result;
    }
}

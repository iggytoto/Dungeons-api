package org.gassangaming.dto.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.barrack.UnitState;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnitDto implements Serializable {
    private Long id;
    private Long ownerId;
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

    private UnitEquip unitEquip;

    public static UnitDto of(Unit u) {
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
                .ownerId(u.getOwnerId())
                .build();
    }

    public static UnitDto of(UnitState us) {
        return UnitDto
                .builder()
                .id(us.getUnit().getId())
                .name(us.getUnit().getName())
                .hitPoints(us.getUnit().getHitPoints())
                .maxHitPoints(us.getUnit().getMaxHitPoints())
                .armor(us.getUnit().getArmor())
                .magicResistance(us.getUnit().getMagicResistance())
                .damage(us.getUnit().getDamage())
                .attackSpeed(us.getUnit().getAttackSpeed())
                .trainingExperience(us.getUnit().getTrainingExperience())
                .attackRange(us.getUnit().getAttackRange())
                .movementSpeed(us.getUnit().getMovementSpeed())
                .activity(us.getUnit().getActivity())
                .battleBehavior(us.getUnit().getBattleBehavior())
                .unitType(us.getUnit().getUnitType())
                .ownerId(us.getUnit().getOwnerId())
                .unitEquip(us.getEquip())
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
        result.setOwnerId(ownerId);
        result.setTrainingExperience(trainingExperience);
        result.setAttackRange(attackRange);
        return result;
    }
}

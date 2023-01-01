package org.gassangaming.model.euqipment.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.UnitEquip;

import javax.persistence.*;

import static org.gassangaming.model.euqipment.human.HumanWarriorEquipment.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class HumanWarriorEquipment extends UnitEquip {

    public static final String DEFENCE_POINTS_COLUMN_NAME = "defence_points";
    public static final String OFFENCE_POINTS_COLUMN_NAME = "offence_points";

    public static final String TABLE_NAME = "human_warrior_equip";

    @Column(name = DEFENCE_POINTS_COLUMN_NAME)
    private int defencePoints = 1;
    @Column(name = OFFENCE_POINTS_COLUMN_NAME)
    private int offencePoints = 1;

    public Valuable getDefenceUpgradeValue() {
        return switch (defencePoints) {
            case 0 -> () -> 1;
            case 1 -> () -> 10;
            case 2 -> () -> 100;
            case 3 -> () -> 1000;
            case 4 -> () -> 10000;
            default -> null;
        };
    }

    public Valuable getOffenceUpgradeValue() {
        return switch (offencePoints) {
            case 0 -> () -> 1;
            case 1 -> () -> 10;
            case 2 -> () -> 100;
            case 3 -> () -> 1000;
            case 4 -> () -> 10000;
            default -> null;
        };
    }

}

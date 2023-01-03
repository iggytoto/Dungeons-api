package org.gassangaming.model.euqipment.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.UnitEquip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.euqipment.human.HumanArcherEquipment.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class HumanArcherEquipment extends UnitEquip {

    public static final String TABLE_NAME = "human_archer_equip";
    public static final String MID_RANGE_POINTS_COLUMN_NAME = "mid_range_points";
    public static final String LONG_RANGE_POINTS_COLUMN_NAME = "long_range_points";
    public static final String FIRE_ARROWS_COLUMN_NAME = "fire_arrows";
    public static final String POISON_ARROWS_COLUMN_NAME = "poison_arrows";

    @Column(name = MID_RANGE_POINTS_COLUMN_NAME)
    int midRangePoints;

    @Column(name = LONG_RANGE_POINTS_COLUMN_NAME)
    int longRangePoints;

    @Column(name = FIRE_ARROWS_COLUMN_NAME)
    boolean fireArrows;

    @Column(name = POISON_ARROWS_COLUMN_NAME)
    boolean poisonArrows;

    public Valuable getMidRangeUpgradeValue() {
        return switch (midRangePoints) {
            case 0 -> () -> 50;
            case 1 -> () -> 500;
            case 2 -> () -> 5000;
            case 3 -> () -> 50000;
            default -> null;
        };
    }

    public Valuable getLongRangeUpgradeValue() {
        return switch (longRangePoints) {
            case 0 -> () -> 50;
            case 1 -> () -> 500;
            case 2 -> () -> 5000;
            case 3 -> () -> 50000;
            default -> null;
        };
    }

    public Valuable getFireArrowsUpgradeValue() {
        return () -> 10000;
    }

    public Valuable getPoisonArrowsUpgradeValue() {
        return () -> 10000;
    }
}

package org.gassangaming.model.euqipment.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.UnitSkills;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.euqipment.human.HumanArcherSkills.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class HumanArcherSkills extends UnitSkills {

    public static final String TABLE_NAME = "human_archer_skills";
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
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            default -> null;
        };
    }

    public Valuable getLongRangeUpgradeValue() {
        return switch (longRangePoints) {
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            default -> null;
        };
    }

    public Valuable getFireArrowsUpgradeValue() {
        return () -> 0;
    }

    public Valuable getPoisonArrowsUpgradeValue() {
        return () -> 0;
    }
}

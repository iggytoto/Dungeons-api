package org.gassangaming.model.euqipment.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.UnitEquip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.euqipment.human.HumanClericEquipment.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class HumanClericEquipment extends UnitEquip {

    public static final String TABLE_NAME = "human_cleric_equip";
    public static final String DISCIPLINE_POINTS_COLUMN_NAME = "discipline_points";
    public static final String SHATTER_COLUMN_NAME = "shatter";
    public static final String DIVINE_COLUMN_NAME = "divine";
    public static final String PURGE_COLUMN_NAME = "purge";

    @Column(name = DISCIPLINE_POINTS_COLUMN_NAME)
    int disciplinePoints;

    @Column(name = SHATTER_COLUMN_NAME)
    boolean shatter;

    @Column(name = DIVINE_COLUMN_NAME)
    boolean divine;
    @Column(name = PURGE_COLUMN_NAME)
    boolean purge;

    public Valuable getMidRangeUpgradeValue() {
        return switch (disciplinePoints) {
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            default -> null;
        };
    }

    public Valuable getShatterUpgradeValue() {
        return () -> 0;
    }

    public Valuable getDivineUpgradeValue() {
        return () -> 0;
    }

    public Valuable getPurgeUpgradeValue() {
        return () -> 0;
    }
}

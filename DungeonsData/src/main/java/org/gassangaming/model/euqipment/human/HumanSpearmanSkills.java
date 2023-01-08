package org.gassangaming.model.euqipment.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.UnitSkills;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.euqipment.human.HumanSpearmanSkills.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class HumanSpearmanSkills extends UnitSkills {

    public static final String DOUBLE_EDGE_POINTS_COLUMN_NAME = "double_edge_points";
    public static final String MID_RANGE_POINTS_COLUMN_NAME = "mid_range_points";

    public static final String TABLE_NAME = "human_spearman_skills";

    @Column(name = DOUBLE_EDGE_POINTS_COLUMN_NAME)
    private int doubleEdgePoints;
    @Column(name = MID_RANGE_POINTS_COLUMN_NAME)
    private int midRangePoints;


    public Valuable getDefenceUpgradeValue() {
        return switch (doubleEdgePoints) {
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            case 4 -> () -> 0;
            default -> null;
        };
    }

    public Valuable getOffenceUpgradeValue() {
        return switch (midRangePoints) {
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            case 4 -> () -> 0;
            default -> null;
        };
    }
}

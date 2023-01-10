package org.gassangaming.model.skills.human;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.skills.UnitSkills;

import javax.persistence.*;

import static org.gassangaming.model.skills.human.HumanWarriorSkills.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class HumanWarriorSkills extends UnitSkills {

    public static final String DEFENCE_POINTS_COLUMN_NAME = "defence_points";
    public static final String OFFENCE_POINTS_COLUMN_NAME = "offence_points";

    public static final String TABLE_NAME = "human_warrior_skills";

    @Column(name = DEFENCE_POINTS_COLUMN_NAME)
    private int defencePoints;
    @Column(name = OFFENCE_POINTS_COLUMN_NAME)
    private int offencePoints;

    public Valuable getDefenceUpgradeValue() {
        return switch (defencePoints) {
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            case 4 -> () -> 0;
            default -> null;
        };
    }

    public Valuable getOffenceUpgradeValue() {
        return switch (offencePoints) {
            case 0 -> () -> 0;
            case 1 -> () -> 0;
            case 2 -> () -> 0;
            case 3 -> () -> 0;
            case 4 -> () -> 0;
            default -> null;
        };
    }

}

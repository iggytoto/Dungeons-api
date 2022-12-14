package org.gassangaming.service.tavern;

import lombok.Getter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.model.unit.human.HumanArcher;
import org.gassangaming.model.unit.human.HumanWarrior;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UnitForSale implements Valuable {
    private static final UnitForSale HUMAN_WARRIOR_TEMPLATE = new UnitForSale(HumanWarrior.ofName("Human Warrior"), 0);
    private static final UnitForSale HUMAN_ARCHER_TEMPLATE = new UnitForSale(HumanArcher.ofName("Human Archer"), 0);
    private static final UnitForSale HUMAN_SPEARMAN_TEMPLATE = new UnitForSale(HumanArcher.ofName("Human Spearman"), 0);
    private static final UnitForSale HUMAN_CLERIC_TEMPLATE = new UnitForSale(HumanArcher.ofName("Human Cleric"), 0);

    private final long goldAmount;
    private final Unit unit;

    private UnitForSale(Unit u, long goldAmount) {
        this.unit = u;
        this.goldAmount = goldAmount;
    }

    @Override
    public long getGoldCost() {
        return goldAmount;
    }

    public static UnitForSale Of(UnitType type) {
        switch (type) {
            case HumanArcher -> {
                return HUMAN_ARCHER_TEMPLATE;
            }
            case HumanWarrior -> {
                return HUMAN_WARRIOR_TEMPLATE;
            }
            case HumanCleric -> {
                return HUMAN_CLERIC_TEMPLATE;
            }
            case HumanSpearman -> {
                return HUMAN_SPEARMAN_TEMPLATE;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static Collection<UnitForSale> All() {
        final var result = new ArrayList<UnitForSale>();
        result.add(HUMAN_WARRIOR_TEMPLATE);
        result.add(HUMAN_ARCHER_TEMPLATE);
        result.add(HUMAN_CLERIC_TEMPLATE);
        result.add(HUMAN_SPEARMAN_TEMPLATE);
        return result;
    }
}

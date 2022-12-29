package org.gassangaming.service.tavern;

import lombok.Getter;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.unit.human.DummyUnit;
import org.gassangaming.model.unit.human.HumanWarrior;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UnitForSale implements Valuable {
    private static final UnitForSale DUMMY_TEMPLATE = new UnitForSale(new DummyUnit(), 0);
    private static final UnitForSale HUMAN_WARRIOR_TEMPLATE = new UnitForSale(new HumanWarrior(), 0);

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
            case Dummy -> {
                return DUMMY_TEMPLATE;
            }
            case HumanWarrior -> {
                return HUMAN_WARRIOR_TEMPLATE;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static Collection<UnitForSale> All() {
        final var result = new ArrayList<UnitForSale>();
        result.add(DUMMY_TEMPLATE);
        result.add(HUMAN_WARRIOR_TEMPLATE);
        return result;
    }
}

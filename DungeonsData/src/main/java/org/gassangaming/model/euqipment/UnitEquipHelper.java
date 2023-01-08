package org.gassangaming.model.euqipment;

import org.gassangaming.model.euqipment.human.HumanArcherSkills;
import org.gassangaming.model.euqipment.human.HumanClericSkills;
import org.gassangaming.model.euqipment.human.HumanSpearmanSkills;
import org.gassangaming.model.euqipment.human.HumanWarriorSkills;
import org.gassangaming.model.unit.UnitType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class UnitEquipHelper {

    private final static HashMap<UnitType, Class<? extends UnitSkills>> unitsToEquipMap = new HashMap<>();

    static {
        unitsToEquipMap.put(UnitType.HumanWarrior, HumanWarriorSkills.class);
        unitsToEquipMap.put(UnitType.HumanArcher, HumanArcherSkills.class);
        unitsToEquipMap.put(UnitType.HumanSpearman, HumanSpearmanSkills.class);
        unitsToEquipMap.put(UnitType.HumanCleric, HumanClericSkills.class);
    }

    public static UnitSkills getDefaultInstanceFor(UnitType type) {
        if (unitsToEquipMap.containsKey(type)) {
            final var c = unitsToEquipMap.get(type);
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}

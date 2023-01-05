package org.gassangaming.model.euqipment;

import org.gassangaming.model.euqipment.human.HumanArcherEquipment;
import org.gassangaming.model.euqipment.human.HumanSpearmanEquipment;
import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;
import org.gassangaming.model.unit.UnitType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class UnitEquipHelper {

    private final static HashMap<UnitType, Class<? extends UnitEquip>> unitsToEquipMap = new HashMap<>();

    static {
        unitsToEquipMap.put(UnitType.HumanWarrior, HumanWarriorEquipment.class);
        unitsToEquipMap.put(UnitType.HumanArcher, HumanArcherEquipment.class);
        unitsToEquipMap.put(UnitType.HumanSpearman, HumanSpearmanEquipment.class);
    }

    public static UnitEquip getDefaultInstanceFor(UnitType type) {
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

    public static Class<? extends UnitEquip> getClassInstanceForClassName(UnitType u) {
        return unitsToEquipMap.getOrDefault(u, null);
    }
}

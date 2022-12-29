package org.gassangaming.model.euqipment;

import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.human.HumanWarrior;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class UnitEquipHelper {

    private final static HashMap<Class<? extends Unit>, Class<? extends UnitEquip>> unitsToEquipMap = new HashMap<>();
    private final static HashMap<String, Class<? extends UnitEquip>> unitClassNameToEquipMap = new HashMap<>();

    static {
        unitsToEquipMap.put(HumanWarrior.class, HumanWarriorEquipment.class);
        unitClassNameToEquipMap.put(HumanWarrior.class.getName(), HumanWarriorEquipment.class);
    }

    public static UnitEquip getDefaultInstanceFor(Unit u) {
        if (unitsToEquipMap.containsKey(u.getClass())) {
            final var c = unitsToEquipMap.get(u.getClass());
            try {
                return c.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Class<? extends UnitEquip> getClassInstanceForClassName(String u) {
        return unitClassNameToEquipMap.getOrDefault(u, null);
    }
}

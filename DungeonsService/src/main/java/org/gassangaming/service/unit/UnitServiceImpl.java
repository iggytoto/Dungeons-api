package org.gassangaming.service.unit;

import org.gassangaming.model.skills.UnitSkills;
import org.gassangaming.model.skills.human.HumanArcherSkills;
import org.gassangaming.model.skills.human.HumanClericSkills;
import org.gassangaming.model.skills.human.HumanSpearmanSkills;
import org.gassangaming.model.skills.human.HumanWarriorSkills;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.barrack.UnitState;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.item.UnitItemsService;
import org.gassangaming.service.unit.skills.CommonUnitSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    private final HashMap<UnitType, Class<? extends UnitSkills>> unitsToEquipMap = new HashMap<>();

    {
        unitsToEquipMap.put(UnitType.HumanWarrior, HumanWarriorSkills.class);
        unitsToEquipMap.put(UnitType.HumanArcher, HumanArcherSkills.class);
        unitsToEquipMap.put(UnitType.HumanSpearman, HumanSpearmanSkills.class);
        unitsToEquipMap.put(UnitType.HumanCleric, HumanClericSkills.class);
    }

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    CommonUnitSkillsService unitSkillsService;
    @Autowired
    UnitItemsService unitItemsService;
    @Autowired
    AccountService accountService;

    @Override
    public Collection<UnitState> getByOwnerId(long ownerId) {
        return unitRepository.findByOwnerId(ownerId).stream().map(unit -> new UnitState(unit, unitSkillsService.getSkillsForUnit(unit), unitItemsService.getForUnit(unit.getId()))).collect(Collectors.toList());
    }

    @Override
    public void changeName(long unitId, String newName, UserContext userContext) throws ServiceException {
        final var unit = unitRepository.findById(unitId);
        checkRights(unit, userContext);
        unit.setName(newName);
        unitRepository.save(unit);
    }

    @Override
    public void changeBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext userContext) throws ServiceException {
        final var unit = unitRepository.findById(unitId);
        checkRights(unit, userContext);
        unit.setBattleBehavior(newBattleBehavior);
        unitRepository.save(unit);
    }

    @Override
    public Unit createNewUnit(Unit unitToSave, UserContext context) throws ServiceException {
        if (unitToSave.getOwnerId() != null) {
            throw new ServiceException("Unit already belongs to someone, cannot save it for given user.");
        }
        unitToSave.setOwnerId(context.getToken().getUserId());
        final var savedUnit = unitRepository.save(unitToSave);
        final var equip = getDefaultInstanceFor(savedUnit.getUnitType());
        if (equip != null) {
            equip.setUnitId(savedUnit.getId());
            unitSkillsService.saveSkills(equip, context);
        }
        return savedUnit;
    }

    private void checkRights(Unit u, UserContext context) throws ServiceException {
        if (u == null) {
            throw new ServiceException("Unit not found");
        }
        if (u.getOwnerId() == null || context.getToken().getUserId() != u.getOwnerId()) {
            throw new ServiceException("You dont have right to change not your units");
        }
    }

    private UnitSkills getDefaultInstanceFor(UnitType type) {
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

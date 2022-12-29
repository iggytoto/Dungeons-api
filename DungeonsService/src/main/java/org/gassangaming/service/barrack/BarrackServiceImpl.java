package org.gassangaming.service.barrack;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.euqipment.UnitEquipHelper;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.UnitEquipRepository;
import org.gassangaming.repository.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class BarrackServiceImpl implements BarrackService {

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    Collection<UnitEquipRepository> equipRepositories;

    private final HashMap<Class<? extends Unit>, UnitEquipRepository> unitRepoCache = new HashMap<>();

    @Override
    public Collection<UnitState> getBarrackUnits(UserContext context) {
        return unitRepository
                .findByOwnerId(context.getToken().getUserId())
                .stream()
                .map(unit -> new UnitState(unit, getEquipForUnit(unit)))
                .collect(Collectors.toList());
    }

    @Override
    public void TrainUnit(long unitId, UserContext context) throws ServiceException {
        final var unitToTrain = unitRepository.findById(unitId);
        checkRights(unitToTrain, context);
        if (!Activity.Idle.equals(unitToTrain.getActivity())) {
            throw new ServiceException("Unit is busy already");
        }
        unitToTrain.setActivity(Activity.Training);
        unitRepository.save(unitToTrain);
    }

    @Override
    public void ChangeUnitName(long unitId, String newName, UserContext context) throws ServiceException {
        final var unit = unitRepository.findById(unitId);
        checkRights(unit, context);
        unit.setName(newName);
        unitRepository.save(unit);
    }

    @Override
    public void ChangeUnitBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext context) throws ServiceException {
        final var unit = unitRepository.findById(unitId);
        checkRights(unit, context);
        unit.setBattleBehavior(newBattleBehavior);
        unitRepository.save(unit);
    }

    private void checkRights(Unit u, UserContext context) throws ServiceException {
        if (u == null) {
            throw new ServiceException("Unit not found");
        }
        if (u.getOwnerId() == null || context.getToken().getUserId() != u.getOwnerId()) {
            throw new ServiceException("You dont have right to change not your units");
        }
    }

    private UnitEquip getEquipForUnit(Unit u) {
        final var r = getRepoForUnit(u);
        if (r != null) {
            return r.getByUnitId(u.getId());
        }
        return null;
    }

    private UnitEquipRepository getRepoForUnit(Unit u) {
        final var unitClass = u.getClass();
        if (!unitRepoCache.containsKey(unitClass)) {
            unitRepoCache.put(
                    unitClass,
                    equipRepositories
                            .stream()
                            .filter(r -> r.getTargetType().equals(UnitEquipHelper.getClassInstanceForClassName(unitClass.getName())))
                            .findFirst().orElse(null));
        }
        return unitRepoCache.get(unitClass);
    }
}

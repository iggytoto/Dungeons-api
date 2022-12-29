package org.gassangaming.service.barrack;

import org.gassangaming.model.Activity;
import org.gassangaming.model.BattleBehavior;
import org.gassangaming.model.Unit;
import org.gassangaming.repository.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BarrackServiceImpl implements BarrackService {

    @Autowired
    UnitRepository unitRepository;

    @Override
    public Collection<Unit> getBarrackUnits(UserContext context) {
        return unitRepository.findByOwnerId(context.getToken().getUserId());
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
}

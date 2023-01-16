package org.gassangaming.service.unit;

import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitRepository unitRepository;

    @Override
    public Collection<Unit> getByOwnerId(long ownerId) {
        return unitRepository.findByOwnerId(ownerId);
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

    private void checkRights(Unit u, UserContext context) throws ServiceException {
        if (u == null) {
            throw new ServiceException("Unit not found");
        }
        if (u.getOwnerId() == null || context.getToken().getUserId() != u.getOwnerId()) {
            throw new ServiceException("You dont have right to change not your units");
        }
    }
}

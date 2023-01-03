package org.gassangaming.service.unit;

import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.barrack.UnitState;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UnitService {

    Collection<UnitState> getByOwnerId(long ownerId);

    void trainUnit(long unitId, UserContext userContext) throws ServiceException;

    void changeName(long unitId, String newName, UserContext userContext) throws ServiceException;

    void changeBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext userContext) throws ServiceException;

    Unit createNewUnit(Unit unitToSave, UserContext context) throws ServiceException;
}

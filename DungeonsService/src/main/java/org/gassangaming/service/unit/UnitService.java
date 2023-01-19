package org.gassangaming.service.unit;

import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Units manipulation service
 */
@Service
public interface UnitService {

    Collection<Unit> getByOwnerId(long ownerId);

    Unit changeName(long unitId, String newName, UserContext userContext) throws ServiceException;

    Unit changeBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext userContext) throws ServiceException;

}

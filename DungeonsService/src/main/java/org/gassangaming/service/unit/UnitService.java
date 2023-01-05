package org.gassangaming.service.unit;

import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.barrack.UnitState;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Units manipulation service
 */
@Service
public interface UnitService {

    Collection<UnitState> getByOwnerId(long ownerId);

    /**
     * @deprecated todo decided to remove training experience from the mvp, should be removed soon
     */
    @Deprecated
    void trainUnit(long unitId, UserContext userContext) throws ServiceException;

    void changeName(long unitId, String newName, UserContext userContext) throws ServiceException;

    void changeBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext userContext) throws ServiceException;

    /**
     * Creates a unit for the given user. Unit should not have owner id before this call. This operation creates also
     * a unit equipment table for the unit and saves it.
     *
     * @param unitToSave unit to save
     * @param context    player context, needs for unit owner id binding
     * @return unit state
     * @throws ServiceException in case unit belongs to someone
     */
    Unit createNewUnit(Unit unitToSave, UserContext context) throws ServiceException;
}

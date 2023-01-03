package org.gassangaming.service.barrack;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Service provides functional for player barracks functions.
 */
@Service
public interface BarrackService {

    /**
     * Gets the available units in barracks for given player
     *
     * @param context player context
     */
    Collection<UnitState> getBarrackUnits(UserContext context);

    /**
     * Sets the unit to training for some time. After that it should gain some experience points
     *
     * @param unitId  unit id to train
     * @param context player context
     * @throws ServiceException in case player doesnt have enough resources to train or given unit id is not players
     */
    @Transactional
    void TrainUnit(long unitId, UserContext context) throws ServiceException;

    @Transactional
    void ChangeUnitName(long unitId, String newName, UserContext context) throws ServiceException;

    @Transactional
    void ChangeUnitBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext context) throws ServiceException;

    @Transactional
    UnitEquip UpgradeUnitEquipment(long equipmentId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException;
}

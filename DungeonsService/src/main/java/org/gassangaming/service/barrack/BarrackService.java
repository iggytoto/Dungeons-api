package org.gassangaming.service.barrack;

import org.gassangaming.model.item.Item;
import org.gassangaming.model.skills.UnitSkills;
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
     * Gets available items for the given player
     */
    Collection<Item> getStoredItems(UserContext context);

    @Transactional
    void equipItem(long itemId, long unitId, UserContext context) throws ServiceException;

    @Transactional
    void unEquipItem(long itemId, UserContext context) throws ServiceException;

    @Transactional
    void ChangeUnitName(long unitId, String newName, UserContext context) throws ServiceException;

    @Transactional
    void ChangeUnitBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext context) throws ServiceException;

    /**
     * Upgrades unit equipment. As unit equipment permanently affects unit characteristics changes unit characteristics
     * that it belongs.
     *
     * @param equipmentId        equipment id to upgrade
     * @param unitType           unit type, this is key mapper because different unit type have different set of equipment
     * @param paramNameToUpgrade key of what concrete should be upgraded
     * @param context            player context
     * @return upgraded unit equipment state
     * @throws ServiceException when it cant be upgraded anymore or equipment id belongs to unit that is not owned by player
     */
    @Transactional
    UnitSkills UpgradeUnitEquipment(long equipmentId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException;
}

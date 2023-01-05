package org.gassangaming.service.unit.equipment;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic entry point to change state of unit equipment. Common service provides mapping between requested equipment
 * by id or by unit id and transfer calls to the concrete {@link UnitEquipmentService}
 */
@Service
public interface CommonUnitEquipmentService {

    /**
     * Generic entry point to upgrade unit equipment. Upgrades any equipment by its owner user id and parameter to upgrade
     *
     * @param equipmentId        equipment id
     * @param unitType           unit type, need for referencing the equipment type <-> unit type
     * @param paramNameToUpgrade parameter of equipment to upgrade
     * @param context            player context
     * @return upgraded equipment state
     * @throws ServiceException in case player doesnt have funds for upgrade or player doesnt own unit of equipment
     */
    @Transactional
    UnitEquip UpgradeUnitEquipment(long equipmentId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException;

    @Transactional
    void saveEquipment(UnitEquip e, UserContext context) throws ServiceException;

    UnitEquip getEquipmentForUnit(Unit u);
}

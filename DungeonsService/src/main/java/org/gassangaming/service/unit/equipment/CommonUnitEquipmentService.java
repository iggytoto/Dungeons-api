package org.gassangaming.service.unit.equipment;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface CommonUnitEquipmentService {

    @Transactional
    UnitEquip UpgradeUnitEquipment(long equipmentId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException;

    @Transactional
    void saveEquipment(UnitEquip e, UserContext context) throws ServiceException;

    UnitEquip getEquipmentForUnit(Unit u);
}

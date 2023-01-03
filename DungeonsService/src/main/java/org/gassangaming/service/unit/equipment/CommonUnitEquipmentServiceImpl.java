package org.gassangaming.service.unit.equipment;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommonUnitEquipmentServiceImpl implements CommonUnitEquipmentService {

    @Autowired
    Collection<UnitEquipmentService> concreteUnitEquipServices;

    @Override
    public UnitEquip UpgradeUnitEquipment(long equipmentId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException {
        return getServiceByUnitType(unitType).upgrade(equipmentId, paramNameToUpgrade, context);
    }

    @Override
    public void saveEquipment(UnitEquip e, UserContext context) throws ServiceException {
        getService(e.getClass()).saveOrUpdateEquip(e);
    }

    @Override
    public UnitEquip getEquipmentForUnit(Unit u) {
        return getServiceByUnitType(u.getUnitType()).getByUnitId(u.getId());
    }

    private <T extends UnitEquip> UnitEquipmentService<T> getServiceByUnitType(UnitType type) {
        return concreteUnitEquipServices.stream().filter(dao -> dao.getTargetUnitType().equals(type)).findFirst().orElse(null);
    }

    private <T extends UnitEquip> UnitEquipmentService<T> getService(Class<T> equip) {
        return concreteUnitEquipServices.stream().filter(dao -> dao.getTargetEquipClass().equals(equip)).findFirst().orElse(null);
    }
}

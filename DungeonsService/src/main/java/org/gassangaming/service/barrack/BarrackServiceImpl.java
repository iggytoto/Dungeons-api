package org.gassangaming.service.barrack;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.unit.UnitService;
import org.gassangaming.service.unit.equipment.CommonUnitEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BarrackServiceImpl implements BarrackService {

    @Autowired
    CommonUnitEquipmentService unitEquipmentService;
    @Autowired
    UnitService unitService;

    @Override
    public Collection<UnitState> getBarrackUnits(UserContext context) {
        return unitService.getByOwnerId(context.getToken().getUserId());
    }

    @Override
    public void ChangeUnitName(long unitId, String newName, UserContext context) throws ServiceException {
        unitService.changeName(unitId, newName, context);
    }

    @Override
    public void ChangeUnitBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext context) throws ServiceException {
        unitService.changeBattleBehavior(unitId, newBattleBehavior, context);
    }

    @Override
    public UnitEquip UpgradeUnitEquipment(long equipmentId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException {
        return unitEquipmentService.UpgradeUnitEquipment(equipmentId, unitType, paramNameToUpgrade, context);
    }
}

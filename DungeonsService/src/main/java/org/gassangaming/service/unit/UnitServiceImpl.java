package org.gassangaming.service.unit;

import org.gassangaming.model.euqipment.UnitEquipHelper;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.barrack.UnitState;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.unit.equipment.CommonUnitEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    CommonUnitEquipmentService unitEquipmentService;

    @Autowired
    AccountService accountService;

    @Override
    public Collection<UnitState> getByOwnerId(long ownerId) {
        return unitRepository.findByOwnerId(ownerId).stream().map(unit -> new UnitState(unit, unitEquipmentService.getEquipmentForUnit(unit))).collect(Collectors.toList());
    }

    @Override
    public void trainUnit(long unitId, UserContext userContext) throws ServiceException {
        final var unitToTrain = unitRepository.findById(unitId);
        checkRights(unitToTrain, userContext);
        if (!Activity.Idle.equals(unitToTrain.getActivity())) {
            throw new ServiceException("Unit is busy already");
        }
        unitToTrain.setActivity(Activity.Training);
        final var trainingCost = unitToTrain.getTrainingCost();
        accountService.buyItem(trainingCost, userContext);
        unitRepository.save(unitToTrain);
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

    @Override
    public void createNewUnit(Unit unitToSave, UserContext context) throws ServiceException {
        if (unitToSave.getOwnerId() != null) {
            throw new ServiceException("Unit already belongs to someone, cannot save it for given user.");
        }
        unitToSave.setOwnerId(context.getToken().getUserId());
        final var savedUnit = unitRepository.save(unitToSave);
        final var equip = UnitEquipHelper.getDefaultInstanceFor(savedUnit.getUnitType());
        if (equip != null) {
            equip.setUnitId(savedUnit.getId());
            unitEquipmentService.saveEquipment(equip, context);
        }
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

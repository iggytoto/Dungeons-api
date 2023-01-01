package org.gassangaming.service.unit.equipment;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.HumanWarriorEquipmentRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanWarriorUnitEquipmentServiceImpl implements UnitEquipmentService<HumanWarriorEquipment> {

    public static final String OffenceParamName = "offence";
    public static final String DefenceParamName = "defence";

    @Autowired
    HumanWarriorEquipmentRepository repository;
    @Autowired
    AccountService accountService;


    @Override
    public Class<HumanWarriorEquipment> getTargetEquipClass() {
        return HumanWarriorEquipment.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanWarrior;
    }

    @Override
    public void upgrade(long eqId, String paramNameToUpgrade, UserContext context) throws ServiceException {
        var eqState = repository.getReferenceById(eqId);
        Valuable upgrade;
        if (OffenceParamName.equals(paramNameToUpgrade)) {
            upgrade = eqState.getOffenceUpgradeValue();
            if (upgrade != null) {
                eqState.setOffencePoints(eqState.getOffencePoints() + 1);
            }
        } else if (DefenceParamName.equals(paramNameToUpgrade)) {
            upgrade = eqState.getDefenceUpgradeValue();
            if (upgrade != null) {
                eqState.setDefencePoints(eqState.getDefencePoints() + 1);
            }
        } else {
            throw new ServiceException("Unknow parameter name to upgrade");
        }
        accountService.buyItem(upgrade, context);
        repository.save(eqState);
    }

    @Override
    public HumanWarriorEquipment saveOrUpdate(HumanWarriorEquipment e) {
        return repository.save(e);
    }

    @Override
    public HumanWarriorEquipment getByUnitId(long id) {
        return repository.getByUnitId(id);
    }
}

package org.gassangaming.service.unit.skills;

import org.gassangaming.model.euqipment.UnitSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommonUnitSkillsServiceImpl implements CommonUnitSkillsService {

    @Autowired
    Collection<UnitSkillsService> concreteUnitEquipServices;

    @Override
    public UnitSkills UpgradeUnitSkills(long skillsId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException {
        return getServiceByUnitType(unitType).upgrade(skillsId, paramNameToUpgrade, context);
    }

    @Override
    public void saveSkills(UnitSkills e, UserContext context) throws ServiceException {
        getService(e.getClass()).saveOrUpdateEquip(e);
    }

    @Override
    public UnitSkills getSkillsForUnit(Unit u) {
        return getServiceByUnitType(u.getUnitType()).getByUnitId(u.getId());
    }

    private <T extends UnitSkills> UnitSkillsService<T> getServiceByUnitType(UnitType type) {
        return concreteUnitEquipServices.stream().filter(dao -> dao.getTargetUnitType().equals(type)).findFirst().orElse(null);
    }

    private <T extends UnitSkills> UnitSkillsService<T> getService(Class<T> equip) {
        return concreteUnitEquipServices.stream().filter(dao -> dao.getTargetEquipClass().equals(equip)).findFirst().orElse(null);
    }
}

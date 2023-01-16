package org.gassangaming.service.tavern;

import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.unit.skills.CommonUnitSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TavernServiceImpl implements TavernService {

    @Autowired
    AccountService accountService;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    CommonUnitSkillsService commonUnitSkillsService;

    @Override
    public Unit buyUnit(UnitType type, long userId) throws ServiceException {
        final var unitForSale = UnitForSale.Of(type);
        accountService.buyItem(unitForSale, userId);
        final var unit = unitForSale.getUnit();
        unit.setOwnerId(userId);
        unitRepository.save(unitForSale.getUnit());
        unit.setSkills(unit.getSkills());
        commonUnitSkillsService.saveSkills(unit.getSkills());
        return unit;
    }
}

package org.gassangaming.service.unit.skills;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.skills.human.HumanClericSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.repository.unit.equip.HumanClericSkillsRepository;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanClericUnitSkillsServiceImpl implements UnitSkillsService<HumanClericSkills> {

    public static final String DISCIPLINE_PARAM_NAME = "discipline";
    public static final String SHATTER_PARAM_NAME = "shatter";
    public static final String DIVINE_PARAM_NAME = "divine";
    public static final String PURGE_PARAM_NAME = "purge";

    @Autowired
    HumanClericSkillsRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    private UnitRepository unitRepository;


    @Override
    public Class<HumanClericSkills> getTargetSkillsClass() {
        return HumanClericSkills.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanCleric;
    }

    @Override
    public HumanClericSkills upgrade(long eqId, String paramNameToUpgrade, long userId) throws ServiceException {
        final var eqState = repository.getReferenceById(eqId);
        Valuable upgrade;
        if (DISCIPLINE_PARAM_NAME.equals(paramNameToUpgrade)) {
            upgrade = eqState.getDisciplineUpgradeValue();
            if (upgrade != null) {
                eqState.setDisciplinePoints(eqState.getDisciplinePoints() + 1);
            }
        } else if (SHATTER_PARAM_NAME.equals(paramNameToUpgrade)) {
            upgrade = eqState.getShatterUpgradeValue();
            if (eqState.isShatter()) {
                throw new ServiceException("Cannot upgrade. Its upgraded already");
            }
            if (upgrade != null) {
                eqState.setShatter(true);
            }
        } else if (DIVINE_PARAM_NAME.equals(paramNameToUpgrade)) {
            upgrade = eqState.getDivineUpgradeValue();
            if (eqState.isDivine()) {
                throw new ServiceException("Cannot upgrade. Its upgraded already");
            }
            if (upgrade != null) {
                eqState.setDivine(true);
            }
        } else if (PURGE_PARAM_NAME.equals(paramNameToUpgrade)) {
            upgrade = eqState.getPurgeUpgradeValue();
            if (eqState.isPurge()) {
                throw new ServiceException("Cannot upgrade. Its upgraded already");
            }
            if (upgrade != null) {
                eqState.setPurge(true);
            }
        } else {
            throw new ServiceException("Unknown parameter name to upgrade");
        }
        accountService.buyItem(upgrade, userId);
        repository.save(eqState);
        unitRepository.save(upgradeUnitChars(eqState, paramNameToUpgrade));
        return eqState;
    }

    private Unit upgradeUnitChars(HumanClericSkills eqState, String paramNameToUpgrade) throws ServiceException {
        final var u = unitRepository.findById(eqState.getUnitId());
        if (paramNameToUpgrade.equals(SHATTER_PARAM_NAME) && eqState.isShatter()) {
            u.setMaxMana(u.getMaxMana() - 10);
            u.setDamage(u.getDamage() + 5);
        } else if (paramNameToUpgrade.equals(DIVINE_PARAM_NAME) && eqState.isDivine()) {
            u.setMaxMana(u.getMaxMana() - 10);
            u.setMagicResistance(u.getMagicResistance() + 10);
        } else if (paramNameToUpgrade.equals(PURGE_PARAM_NAME) && eqState.isPurge()) {
            u.setMaxMana(u.getMaxMana() - 10);
            u.setDamage(u.getDamage() + 5);
        } else {
            throw new ServiceException("Unknown parameter name to upgrade");
        }
        return u;
    }

    @Override
    public HumanClericSkills saveOrUpdate(HumanClericSkills e) {
        return repository.save(e);
    }

    @Override
    public HumanClericSkills getByUnitId(long id) {
        return repository.getByUnitId(id);
    }
}

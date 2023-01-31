package org.gassangaming.service.unit.skills;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.skills.human.HumanArcherSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.repository.unit.equip.HumanArcherSkillsRepository;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanArcherUnitSkillsServiceImpl implements UnitSkillsService<HumanArcherSkills> {

    public static final String MID_RANGE_PARAM = "midRange";
    public static final String LONG_RANGE_PARAM = "longRange";
    public static final String FIRE_ARROW_PARAM = "fireArrow";
    public static final String POISON_ARROW_PARAM = "poisonArrow";

    @Autowired
    HumanArcherSkillsRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    UnitRepository unitRepository;

    @Override
    public Class<HumanArcherSkills> getTargetSkillsClass() {
        return HumanArcherSkills.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanArcher;
    }

    @Override
    public HumanArcherSkills upgrade(long eqId, String paramNameToUpgrade, long userId) throws ServiceException {
        final var eqState = repository.findById(eqId).orElseThrow();
        Valuable upgrade;
        if (MID_RANGE_PARAM.equals(paramNameToUpgrade)) {
            upgrade = eqState.getMidRangeUpgradeValue();
            if (eqState.getMidRangePoints() == 4) {
                throw new ServiceException("Cannot upgrade. Its maximum level already");
            }
            if (upgrade != null) {
                eqState.setMidRangePoints(eqState.getMidRangePoints() + 1);
            }
        } else if (LONG_RANGE_PARAM.equals(paramNameToUpgrade)) {
            upgrade = eqState.getLongRangeUpgradeValue();
            if (eqState.getLongRangePoints() == 4) {
                throw new ServiceException("Cannot upgrade. Its maximum level already");
            }
            if (upgrade != null) {
                eqState.setLongRangePoints(eqState.getLongRangePoints() + 1);
            }
        } else if (FIRE_ARROW_PARAM.equals(paramNameToUpgrade)) {
            upgrade = eqState.getFireArrowsUpgradeValue();
            if (eqState.isFireArrows()) {
                throw new ServiceException("Cannot upgrade. Its upgraded already");
            }
            if (upgrade != null) {
                eqState.setFireArrows(true);
            }
        } else if (POISON_ARROW_PARAM.equals(paramNameToUpgrade)) {
            upgrade = eqState.getPoisonArrowsUpgradeValue();
            if (eqState.isPoisonArrows()) {
                throw new ServiceException("Cannot upgrade. Its upgraded already");
            }
            if (upgrade != null) {
                eqState.setPoisonArrows(true);
            }
        } else {
            throw new ServiceException("Unknown parameter name to upgrade");
        }
        accountService.buyItem(upgrade, userId);
        repository.save(eqState);
        unitRepository.save(upgradeUnitChars(eqState, paramNameToUpgrade));
        return eqState;
    }

    private Unit upgradeUnitChars(HumanArcherSkills eqState, String paramNameToUpgrade) {
        final var u = unitRepository.findById(eqState.getUnitId());
        if (paramNameToUpgrade.equals(MID_RANGE_PARAM)) {
            switch (eqState.getMidRangePoints()) {
                case 1 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.5f);
                    u.setMovementSpeed(u.getMovementSpeed() + .25f);
                }
                case 2 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.75f);
                    u.setMovementSpeed(u.getMovementSpeed() + .5f);
                }
                case 3 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 2f);
                    u.setMovementSpeed(u.getMovementSpeed() + .75f);
                }
                case 4 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 2.15f);
                    u.setMovementSpeed(u.getMovementSpeed() + 1f);
                }
                default -> {
                }
            }
        } else if (paramNameToUpgrade.equals(LONG_RANGE_PARAM)) {
            switch (eqState.getLongRangePoints()) {
                case 1 -> {
                    u.setAttackRange(u.getAttackRange() + 2.5f);
                    u.setDamage(u.getDamage() + 10);
                    u.setMovementSpeed(u.getMovementSpeed() - .5f);
                }
                case 2 -> {
                    u.setAttackRange(u.getAttackRange() + 5f);
                    u.setDamage(u.getDamage() + 20);
                    u.setMovementSpeed(u.getMovementSpeed() - 15f);
                }
                case 3 -> {
                    u.setAttackRange(u.getAttackRange() + 6.5f);
                    u.setDamage(u.getDamage() + 30);
                    u.setMovementSpeed(u.getMovementSpeed() - 1.25f);
                }
                case 4 -> {
                    u.setAttackRange(u.getAttackRange() + 7.5f);
                    u.setDamage(u.getDamage() + 45);
                    u.setMovementSpeed(u.getMovementSpeed() - 1.5f);
                }
                default -> {
                }
            }
        }
        return u;
    }

    @Override
    public HumanArcherSkills saveOrUpdate(HumanArcherSkills e) {
        return repository.save(e);
    }

    @Override
    public HumanArcherSkills getByUnitId(long id) {
        return repository.getByUnitId(id);
    }
}

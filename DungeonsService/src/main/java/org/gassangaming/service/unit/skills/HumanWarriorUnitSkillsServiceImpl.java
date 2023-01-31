package org.gassangaming.service.unit.skills;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.skills.human.HumanWarriorSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.repository.unit.equip.HumanWarriorSkillsRepository;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanWarriorUnitSkillsServiceImpl implements UnitSkillsService<HumanWarriorSkills> {

    public static final String OffenceParamName = "offence";
    public static final String DefenceParamName = "defence";

    @Autowired
    HumanWarriorSkillsRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    private UnitRepository unitRepository;


    @Override
    public Class<HumanWarriorSkills> getTargetSkillsClass() {
        return HumanWarriorSkills.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanWarrior;
    }

    @Override
    public HumanWarriorSkills upgrade(long eqId, String paramNameToUpgrade, long userId) throws ServiceException {
        final var eqState = repository.getReferenceById(eqId);
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
        accountService.buyItem(upgrade, userId);
        repository.save(eqState);
        unitRepository.save(upgradeUnitChars(eqState, paramNameToUpgrade));
        return eqState;
    }

    private Unit upgradeUnitChars(HumanWarriorSkills eqState, String paramNameToUpgrade) {
        final var u = unitRepository.findById(eqState.getUnitId());
        if (paramNameToUpgrade.equals(OffenceParamName)) {
            switch (eqState.getOffencePoints()) {
                case 1 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.5f);
                    u.setAttackRange(u.getAttackRange() + .1f);
                    u.setDamage(u.getDamage() + 10);
                }
                case 2 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.66f);
                    u.setAttackRange(u.getAttackRange() + .11f);
                    u.setDamage(u.getDamage() + 15);
                }
                case 3 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.75f);
                    u.setAttackRange(u.getAttackRange() + .12f);
                    u.setDamage(u.getDamage() + 20);
                }
                case 4 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.8f);
                    u.setAttackRange(u.getAttackRange() + .125f);
                    u.setDamage(u.getDamage() + 25);
                }
                case 5 -> {
                    u.setAttackSpeed(u.getAttackSpeed() * 1.85f);
                    u.setAttackRange(u.getAttackRange() + .125f);
                    u.setDamage(u.getDamage() + 30);
                }
                default -> {
                }
            }
        } else {
            switch (eqState.getDefencePoints()) {
                case 1 -> {
                    u.setMaxHitPoints(u.getMaxHitPoints() + 50);
                    u.setArmor(u.getArmor() + 10);
                    u.setMovementSpeed(u.getMovementSpeed() - .5f);
                }
                case 2 -> {
                    u.setMaxHitPoints(u.getMaxHitPoints() + 75);
                    u.setArmor(u.getArmor() + 20);
                    u.setMovementSpeed(u.getMovementSpeed() - 1f);
                }
                case 3 -> {
                    u.setMaxHitPoints(u.getMaxHitPoints() + 100);
                    u.setArmor(u.getArmor() + 25);
                    u.setMovementSpeed(u.getMovementSpeed() - 1.25f);
                }
                case 4 -> {
                    u.setMaxHitPoints(u.getMaxHitPoints() + 125);
                    u.setArmor(u.getArmor() + 30);
                    u.setMovementSpeed(u.getMovementSpeed() - 1.5f);
                }
                case 5 -> {
                    u.setMaxHitPoints(u.getMaxHitPoints() + 135);
                    u.setArmor(u.getArmor() + 30);
                    u.setMovementSpeed(u.getMovementSpeed() - 1.5f);
                    u.setMagicResistance(u.getMagicResistance() + 15);
                }
                default -> {
                }
            }
        }
        return u;
    }

    @Override
    public HumanWarriorSkills saveOrUpdate(HumanWarriorSkills e) {
        return repository.save(e);
    }

    @Override
    public HumanWarriorSkills getByUnitId(long id) {
        return repository.getByUnitId(id);
    }
}

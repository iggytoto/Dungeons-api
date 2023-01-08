package org.gassangaming.service.unit.skills;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.human.HumanWarriorSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.model.unit.human.HumanWarrior;
import org.gassangaming.repository.unit.equip.HumanWarriorSkillsRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.UserContext;
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
    public Class<HumanWarriorSkills> getTargetEquipClass() {
        return HumanWarriorSkills.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanWarrior;
    }

    @Override
    public HumanWarriorSkills upgrade(long eqId, String paramNameToUpgrade, UserContext context) throws ServiceException {
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
        accountService.buyItem(upgrade, context);
        repository.save(eqState);
        unitRepository.save(upgradeUnitChars(eqState, paramNameToUpgrade));
        return eqState;
    }

    private Unit upgradeUnitChars(HumanWarriorSkills eqState, String paramNameToUpgrade) {
        final var u = unitRepository.findById(eqState.getUnitId());
        if (paramNameToUpgrade.equals(OffenceParamName)) {
            switch (eqState.getOffencePoints()) {
                case 1 -> {
                    u.setAttackSpeed(HumanWarrior.ATTACK_SPEED_BASE * 1.5f);
                    u.setAttackRange(HumanWarrior.ATTACK_RANGE_BASE + .1f);
                    u.setDamage(HumanWarrior.DAMAGE_BASE + 10);
                }
                case 2 -> {
                    u.setAttackSpeed(HumanWarrior.ATTACK_SPEED_BASE * 1.66f);
                    u.setAttackRange(HumanWarrior.ATTACK_RANGE_BASE + .11f);
                    u.setDamage(HumanWarrior.DAMAGE_BASE + 15);
                }
                case 3 -> {
                    u.setAttackSpeed(HumanWarrior.ATTACK_SPEED_BASE * 1.75f);
                    u.setAttackRange(HumanWarrior.ATTACK_RANGE_BASE + .12f);
                    u.setDamage(HumanWarrior.DAMAGE_BASE + 20);
                }
                case 4 -> {
                    u.setAttackSpeed(HumanWarrior.ATTACK_SPEED_BASE * 1.8f);
                    u.setAttackRange(HumanWarrior.ATTACK_RANGE_BASE + .125f);
                    u.setDamage(HumanWarrior.DAMAGE_BASE + 25);
                }
                case 5 -> {
                    u.setAttackSpeed(HumanWarrior.ATTACK_SPEED_BASE * 1.85f);
                    u.setAttackRange(HumanWarrior.ATTACK_RANGE_BASE + .125f);
                    u.setDamage(HumanWarrior.DAMAGE_BASE + 30);
                }
                default -> {
                }
            }
        } else {
            switch (eqState.getDefencePoints()) {
                case 1 -> {
                    u.setMaxHitPoints(HumanWarrior.HP_BASE + 50);
                    u.setArmor(HumanWarrior.ARMOR_BASE + 10);
                    u.setMovementSpeed(HumanWarrior.MOVE_SPEED_BASE - .5f);
                }
                case 2 -> {
                    u.setMaxHitPoints(HumanWarrior.HP_BASE + 75);
                    u.setArmor(HumanWarrior.ARMOR_BASE + 20);
                    u.setMovementSpeed(HumanWarrior.MOVE_SPEED_BASE - 1f);
                }
                case 3 -> {
                    u.setMaxHitPoints(HumanWarrior.HP_BASE + 100);
                    u.setArmor(HumanWarrior.ARMOR_BASE + 25);
                    u.setMovementSpeed(HumanWarrior.MOVE_SPEED_BASE - 1.25f);
                }
                case 4 -> {
                    u.setMaxHitPoints(HumanWarrior.HP_BASE + 125);
                    u.setArmor(HumanWarrior.ARMOR_BASE + 30);
                    u.setMovementSpeed(HumanWarrior.MOVE_SPEED_BASE - 1.5f);
                }
                case 5 -> {
                    u.setMaxHitPoints(HumanWarrior.HP_BASE + 135);
                    u.setArmor(HumanWarrior.ARMOR_BASE + 30);
                    u.setMovementSpeed(HumanWarrior.MOVE_SPEED_BASE - 1.5f);
                    u.setMagicResistance(HumanWarrior.MR_BASE + 15);
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

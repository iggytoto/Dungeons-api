package org.gassangaming.service.unit.equipment;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.euqipment.human.HumanArcherEquipment;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.model.unit.human.HumanArcher;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.repository.unit.equip.HumanArcherEquipmentRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

public class HumanArcherUnitEquipmentServiceImpl implements UnitEquipmentService<HumanArcherEquipment> {

    public static final String MID_RANGE_PARAM = "midRange";
    public static final String LONG_RANGE_PARAM = "longRange";
    public static final String FIRE_ARROW_PARAM = "fireArrow";
    public static final String POISON_ARROW_PARAM = "poisonArrow";

    @Autowired
    HumanArcherEquipmentRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    UnitRepository unitRepository;

    @Override
    public Class<HumanArcherEquipment> getTargetEquipClass() {
        return HumanArcherEquipment.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanArcher;
    }

    @Override
    public HumanArcherEquipment upgrade(long eqId, String paramNameToUpgrade, UserContext context) throws ServiceException {
        final var eqState = repository.getReferenceById(eqId);
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
            upgrade = eqState.getMidRangeUpgradeValue();
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
            throw new ServiceException("Unknow parameter name to upgrade");
        }
        accountService.buyItem(upgrade, context);
        repository.save(eqState);
        unitRepository.save(upgradeUnitChars(eqState, paramNameToUpgrade));
        return eqState;
    }

    private Unit upgradeUnitChars(HumanArcherEquipment eqState, String paramNameToUpgrade) {
        final var u = unitRepository.findById(eqState.getUnitId());
        if (paramNameToUpgrade.equals(MID_RANGE_PARAM)) {
            switch (eqState.getMidRangePoints()) {
                case 1 -> {
                    u.setAttackSpeed(HumanArcher.ATTACK_SPEED_BASE * 1.5f);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE + .25f);
                }
                case 2 -> {
                    u.setAttackSpeed(HumanArcher.ATTACK_SPEED_BASE * 1.75f);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE + .5f);
                }
                case 3 -> {
                    u.setAttackSpeed(HumanArcher.ATTACK_SPEED_BASE * 2f);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE + .75f);
                }
                case 4 -> {
                    u.setAttackSpeed(HumanArcher.ATTACK_SPEED_BASE * 2.15f);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE + 1f);
                }
                default -> {
                }
            }
        } else if (paramNameToUpgrade.equals(LONG_RANGE_PARAM)) {
            switch (eqState.getLongRangePoints()) {
                case 1 -> {
                    u.setAttackRange(HumanArcher.ATTACK_RANGE_BASE + 2.5f);
                    u.setDamage(HumanArcher.DAMAGE_BASE + 10);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE - .5f);
                }
                case 2 -> {
                    u.setAttackRange(HumanArcher.ATTACK_RANGE_BASE + 5f);
                    u.setDamage(HumanArcher.DAMAGE_BASE + 20);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE - 15f);
                }
                case 3 -> {
                    u.setAttackRange(HumanArcher.ATTACK_RANGE_BASE + 6.5f);
                    u.setDamage(HumanArcher.DAMAGE_BASE + 30);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE - 1.25f);
                }
                case 4 -> {
                    u.setAttackRange(HumanArcher.ATTACK_RANGE_BASE + 7.5f);
                    u.setDamage(HumanArcher.DAMAGE_BASE + 45);
                    u.setMovementSpeed(HumanArcher.MOVE_SPEED_BASE - 1.5f);
                }
                default -> {
                }
            }
        }
        return u;
    }

    @Override
    public HumanArcherEquipment saveOrUpdate(HumanArcherEquipment e) {
        return repository.save(e);
    }

    @Override
    public HumanArcherEquipment getByUnitId(long id) {
        return repository.getByUnitId(id);
    }
}

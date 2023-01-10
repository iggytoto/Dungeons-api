package org.gassangaming.service.unit.skills;

import org.gassangaming.model.Valuable;
import org.gassangaming.model.skills.human.HumanSpearmanSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.model.unit.human.HumanSpearman;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.repository.unit.equip.HumanSpearmanSkillsRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanSpearmanUnitSkillsServiceImpl implements UnitSkillsService<HumanSpearmanSkills> {

    public static final String DOUBLE_EDGE_PARAM_NAME = "doubleEdge";
    public static final String MID_RANGE_PARAM_NAME = "midRange";

    @Autowired
    HumanSpearmanSkillsRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    private UnitRepository unitRepository;


    @Override
    public Class<HumanSpearmanSkills> getTargetEquipClass() {
        return HumanSpearmanSkills.class;
    }

    @Override
    public UnitType getTargetUnitType() {
        return UnitType.HumanSpearman;
    }

    @Override
    public HumanSpearmanSkills upgrade(long eqId, String paramNameToUpgrade, UserContext context) throws ServiceException {
        final var eqState = repository.getReferenceById(eqId);
        Valuable upgrade;
        if (DOUBLE_EDGE_PARAM_NAME.equals(paramNameToUpgrade)) {
            upgrade = eqState.getOffenceUpgradeValue();
            if (upgrade != null) {
                eqState.setDoubleEdgePoints(eqState.getDoubleEdgePoints() + 1);
            }
        } else if (MID_RANGE_PARAM_NAME.equals(paramNameToUpgrade)) {
            upgrade = eqState.getDefenceUpgradeValue();
            if (upgrade != null) {
                eqState.setMidRangePoints(eqState.getMidRangePoints() + 1);
            }
        } else {
            throw new ServiceException("Unknow parameter name to upgrade");
        }
        accountService.buyItem(upgrade, context);
        repository.save(eqState);
        unitRepository.save(upgradeUnitChars(eqState, paramNameToUpgrade));
        return eqState;
    }

    private Unit upgradeUnitChars(HumanSpearmanSkills eqState, String paramNameToUpgrade) {
        final var u = unitRepository.findById(eqState.getUnitId());
        if (paramNameToUpgrade.equals(MID_RANGE_PARAM_NAME)) {
            switch (eqState.getMidRangePoints()) {
                case 1 -> u.setDamage(HumanSpearman.HP_BASE + 15);
                case 2 -> u.setDamage(HumanSpearman.HP_BASE + 25);
                case 3 -> u.setDamage(HumanSpearman.HP_BASE + 35);
                case 4 -> u.setDamage(HumanSpearman.HP_BASE + 45);
                case 5 -> u.setDamage(HumanSpearman.HP_BASE + 60);
                default -> {
                }
            }
        }
        return u;
    }

    @Override
    public HumanSpearmanSkills saveOrUpdate(HumanSpearmanSkills e) {
        return repository.save(e);
    }

    @Override
    public HumanSpearmanSkills getByUnitId(long id) {
        return repository.getByUnitId(id);
    }
}

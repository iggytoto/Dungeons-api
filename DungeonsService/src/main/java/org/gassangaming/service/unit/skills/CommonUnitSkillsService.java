package org.gassangaming.service.unit.skills;

import org.gassangaming.model.skills.UnitSkills;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic entry point to change state of unit skills. Common service provides mapping between requested skills
 * by id or by unit id and transfer calls to the concrete {@link UnitSkillsService}
 */
@Service
public interface CommonUnitSkillsService {

    /**
     * Generic entry point to upgrade unit skills. Upgrades any skills by its owner user id and parameter to upgrade
     *
     * @param skillsId        skills id
     * @param unitType           unit type, need for referencing the skills type <-> unit type
     * @param paramNameToUpgrade parameter of skills to upgrade
     * @param context            player context
     * @return upgraded skills state
     * @throws ServiceException in case player doesnt have funds for upgrade or player doesnt own unit of skills
     */
    @Transactional
    UnitSkills UpgradeUnitSkills(long skillsId, UnitType unitType, String paramNameToUpgrade, UserContext context) throws ServiceException;

    @Transactional
    void saveSkills(UnitSkills e) throws ServiceException;

    UnitSkills getSkillsForUnit(Unit u);
}

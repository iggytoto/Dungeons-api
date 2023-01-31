package org.gassangaming.repository.unit.equip;

import org.gassangaming.model.skills.human.HumanWarriorSkills;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface HumanWarriorSkillsRepository extends UnitSkillsRepository<HumanWarriorSkills> {

    String GET_BY_UNIT_ID_QUERY = "select e from HumanWarriorSkills e where e.unitId=:" + PARAM_1;

    @Query(value = GET_BY_UNIT_ID_QUERY)
    @Override
    HumanWarriorSkills getByUnitId(@Param(PARAM_1) long unitId);
}

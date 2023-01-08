package org.gassangaming.repository.unit.equip;

import org.gassangaming.model.euqipment.human.HumanSpearmanSkills;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface HumanSpearmanSkillsRepository extends UnitEquipRepository<HumanSpearmanSkills> {

    String GET_BY_UNIT_ID_QUERY = "select e from HumanSpearmanSkills e where e.unitId=:" + PARAM_1;

    @Query(value = GET_BY_UNIT_ID_QUERY)
    @Override
    HumanSpearmanSkills getByUnitId(@Param(PARAM_1) long unitId);
}

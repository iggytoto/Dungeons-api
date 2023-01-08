package org.gassangaming.repository.unit.equip;

import org.gassangaming.model.euqipment.human.HumanArcherSkills;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface HumanArcherSkillsRepository extends UnitEquipRepository<HumanArcherSkills> {

    String GET_BY_UNIT_ID_QUERY = "select e from HumanArcherSkills e where e.unitId=:" + PARAM_1;

    @Query(value = GET_BY_UNIT_ID_QUERY)
    @Override
    HumanArcherSkills getByUnitId(@Param(PARAM_1) long unitId);
}

package org.gassangaming.repository;

import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface HumanWarriorEquipmentRepository extends UnitEquipRepository<HumanWarriorEquipment> {

    String GET_BY_UNIT_ID_QUERY = "select e from HumanWarriorEquipment e where e.unitId=:" + PARAM_1;

    @Query(value = GET_BY_UNIT_ID_QUERY)
    @Override
    HumanWarriorEquipment getByUnitId(@Param(PARAM_1) long unitId);
}

package org.gassangaming.repository.unit.equip;

import org.gassangaming.model.euqipment.human.HumanClericEquipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface HumanClericEquipmentRepository extends UnitEquipRepository<HumanClericEquipment> {

    String GET_BY_UNIT_ID_QUERY = "select e from HumanClericEquipment e where e.unitId=:" + PARAM_1;

    @Query(value = GET_BY_UNIT_ID_QUERY)
    @Override
    HumanClericEquipment getByUnitId(@Param(PARAM_1) long unitId);
}

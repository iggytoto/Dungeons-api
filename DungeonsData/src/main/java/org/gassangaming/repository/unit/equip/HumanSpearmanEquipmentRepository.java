package org.gassangaming.repository.unit.equip;

import org.gassangaming.model.euqipment.human.HumanSpearmanEquipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface HumanSpearmanEquipmentRepository extends UnitEquipRepository<HumanSpearmanEquipment> {

    String GET_BY_UNIT_ID_QUERY = "select e from HumanSpearmanEquipment e where e.unitId=:" + PARAM_1;

    @Query(value = GET_BY_UNIT_ID_QUERY)
    @Override
    HumanSpearmanEquipment getByUnitId(@Param(PARAM_1) long unitId);
}

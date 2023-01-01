package org.gassangaming.repository;

import org.gassangaming.model.euqipment.UnitEquip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

@NoRepositoryBean
public interface UnitEquipRepository<T extends UnitEquip> extends JpaRepository<T, Long> {

    T getByUnitId(@Param(PARAM_1) long unitId);
}

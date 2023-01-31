package org.gassangaming.repository.unit.equip;

import org.gassangaming.model.skills.UnitSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

@NoRepositoryBean
public interface UnitSkillsRepository<T extends UnitSkills> extends JpaRepository<T, Long> {

    T getByUnitId(@Param(PARAM_1) long unitId);
}

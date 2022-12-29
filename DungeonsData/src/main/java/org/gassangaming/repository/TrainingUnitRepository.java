package org.gassangaming.repository;

import org.gassangaming.model.unit.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, TrainingUnit> {

    String DELETE_ALL_FOR_USER_QUERY = "DELETE FROM training_units tu WHERE tu." + TrainingUnit.USER_ID_COLUMN_NAME + " = :" + PARAM_1;
    String GET_UNITS_FOR_USER_QUERY = "SELECT tu FROM TrainingUnit tu WHERE tu.userId=:" + PARAM_1;

    @Modifying
    @Query(value = DELETE_ALL_FOR_USER_QUERY, nativeQuery = true)
    void deleteAllForUser(@Param(PARAM_1) long userId);

    @Query(value = GET_UNITS_FOR_USER_QUERY)
    Collection<TrainingUnit> getUnitsForUser(@Param(PARAM_1) long userId);

}

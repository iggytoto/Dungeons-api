package org.gassangaming.repository;

import org.gassangaming.model.Activity;
import org.gassangaming.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.*;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    String SET_OWNER_QUERY = "UPDATE units SET " + Unit.OWNER_ID_COLUMN_NAME + " =:" + PARAM_2 + " WHERE id=:" + PARAM_1;
    String FIND_BY_OWNER_ID_QUERY = "select u from Unit u where u.ownerId=:" + PARAM_1;
    String FIND_BY_ID_QUERY = "select u from Unit u where u.id=:" + PARAM_1;
    String UPDATE_ACTIVITY_BY_UNIT_ID_QUERY = "update Unit u set u.activity=:" + PARAM_2 + " where u.id=:" + PARAM_1;
    String FIND_BY_ACTIVITY_QUERY = "select u from Unit u where u.activity=:" + PARAM_1;
    @Modifying
    @Query(value = SET_OWNER_QUERY, nativeQuery = true)
    void setOwner(@Param(PARAM_1) long unitId, @Param(PARAM_2) long userId);

    @Query(value = FIND_BY_OWNER_ID_QUERY)
    Collection<Unit> findByOwnerId(@Param(PARAM_1) long userId);

    @Query(value = FIND_BY_ID_QUERY)
    Unit findById(@Param(PARAM_1) long unitId);

    @Modifying
    @Query(value = UPDATE_ACTIVITY_BY_UNIT_ID_QUERY)
    void updateActivityByUnitId(@Param(PARAM_1) long unitId, @Param(PARAM_2) Activity idle);

    @Query(value = FIND_BY_ACTIVITY_QUERY)
    Collection<Unit> findByActivity(@Param(PARAM_1) Activity activity);

}

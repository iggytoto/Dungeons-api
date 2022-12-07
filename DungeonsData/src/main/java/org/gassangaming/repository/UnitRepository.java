package org.gassangaming.repository;

import org.gassangaming.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.UNIT_ID_PARAM;
import static org.gassangaming.repository.Constants.USER_ID_PARAM;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    String SET_OWNER_QUERY = "UPDATE units SET " + Unit.OWNER_ID_COLUMN_NAME + " =:" + USER_ID_PARAM + " WHERE id=:" + UNIT_ID_PARAM;
    String FIND_BY_OWNER_ID_QUERY = "select u from Unit u where u.ownerId=:" + USER_ID_PARAM;
    String FIND_BY_ID_QUERY = "select u from Unit u where u.id=:" + USER_ID_PARAM;

    @Modifying
    @Query(value = SET_OWNER_QUERY, nativeQuery = true)
    void setOwner(@Param(UNIT_ID_PARAM) long unitId, @Param(USER_ID_PARAM) long userId);

    @Query(value = FIND_BY_OWNER_ID_QUERY)
    Collection<Unit> findByOwnerIdD(@Param(USER_ID_PARAM) long userId);

    @Query(value = FIND_BY_ID_QUERY)
    Unit findById(@Param(UNIT_ID_PARAM) long unitId);
}

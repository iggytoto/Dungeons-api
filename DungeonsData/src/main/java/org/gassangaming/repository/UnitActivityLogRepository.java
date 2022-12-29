package org.gassangaming.repository;

import org.gassangaming.model.unit.UnitActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface UnitActivityLogRepository extends JpaRepository<UnitActivityLog, Long> {

    String FIND_BY_ACTIVITY_QUERY = "SELECT * FROM unit_activity_log ual JOIN units u ON u.id=ual.unit_id where u.activity=:" + PARAM_1;

    @Query(value = FIND_BY_ACTIVITY_QUERY, nativeQuery = true)
    Collection<UnitActivityLog> findByActivity(@Param(PARAM_1) String activityAsString);
}

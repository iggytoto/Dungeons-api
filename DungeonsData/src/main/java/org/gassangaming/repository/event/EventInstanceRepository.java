package org.gassangaming.repository.event;

import org.gassangaming.model.event.EventInstance;
import org.gassangaming.model.event.EventInstanceStatus;
import org.gassangaming.model.unit.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface EventInstanceRepository extends JpaRepository<EventInstance, Long> {

    String FIND_FIRST_LATEST_WAITING_FOR_SERVER = "SELECT ei FROM EventInstance ei WHERE ei.status =:" + PARAM_1;
    String COUNT_BY_EVENT_ID = "SELECT COUNT(ei) FROM EventInstance ei WHERE ei.eventId =:" + PARAM_1;
    String FIND_ALL_BY_USER_ID = "SELECT ei FROM EventInstance ei LEFT JOIN UserEventInstance uei ON ei.eventId = uei.eventId WHERE uei.userId =:" + PARAM_1;
    String FIND_ALL_UNITS_BY_ID = "SELECT u FROM EventInstance ei JOIN UserEventInstance uei ON ei.id = uei.eventInstanceId JOIN UnitEventRegistration uer ON uer.userId = uei.userId JOIN Unit u ON u.id = uer.unitId WHERE ei.id=:" + PARAM_1;

    @Query(value = FIND_FIRST_LATEST_WAITING_FOR_SERVER)
    EventInstance findFirstLatestWaitingForServer(@Param(PARAM_1) EventInstanceStatus waitingForServer);

    //    @Query(value = COUNT_BY_EVENT_ID)
    int countByEventId(@Param(PARAM_1) long eventId);

    @Query(value = FIND_ALL_UNITS_BY_ID)
    Collection<Unit> findAllUnitsById(@Param(PARAM_1) long eventInstanceId);

    @Query(value = FIND_ALL_BY_USER_ID)
    Collection<EventInstance> findAllByUserId(@Param(PARAM_1) long userId);
}

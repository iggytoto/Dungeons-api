package org.gassangaming.repository.event;

import org.gassangaming.model.event.EventInstance;
import org.gassangaming.model.event.EventInstanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface EventInstanceRepository extends JpaRepository<EventInstance, Long> {

    String FIND_FIRST_LATEST_WAITING_FOR_SERVER = "SELECT ei FROM EventInstance ei WHERE ei.status =:" + PARAM_1;
    String COUNT_BY_EVENT_ID = "SELECT COUNT(ei) FROM EventInstance ei WHERE ei.eventId =:" + PARAM_1;

    @Query(value = FIND_FIRST_LATEST_WAITING_FOR_SERVER)
    EventInstance findFirstLatestWaitingForServer(@Param(PARAM_1) EventInstanceStatus waitingForServer);

    @Query(value = COUNT_BY_EVENT_ID)
    int countByEventId(@Param(PARAM_1)long eventId);
}

package org.gassangaming.repository.event;

import org.gassangaming.model.event.Event;
import org.gassangaming.model.event.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface EventRepository extends JpaRepository<Event, Long> {

    String FIND_ALL_FOR_USER = "SELECT e FROM Event e JOIN UserEventRegistration ur ON e.id = ur.eventId WHERE ur.userId=:" + PARAM_1;
    String FIND_LATEST_PLANNED_BY_TYPE = "SELECT e FROM Event e WHERE e.status = 'Planned' and e.eventType=:" + PARAM_1;
    String SET_CLOSED_BY_ID = "UPDATE Event SET status = 'Closed' WHERE id=:" + PARAM_1;

    @Query(value = FIND_ALL_FOR_USER)
    Collection<Event> findAllForUser(@Param(PARAM_1) long id);

    @Query(value = FIND_LATEST_PLANNED_BY_TYPE)
    Event findLatestPlannedByType(@Param(PARAM_1) EventType eventType);

    @Query(value = SET_CLOSED_BY_ID)
    @Modifying
    void setClosedById(@Param(PARAM_1) long eventId);
}

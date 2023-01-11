package org.gassangaming.service.event;

import org.gassangaming.model.event.Event;
import org.gassangaming.model.event.EventType;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Entry points for accepting participations in events for players
 */
@Service
public interface EventsService {

    @Transactional
    /**
     * Register player ream participation in event
     */
    void register(Collection<Long> unitsIds, EventType eventType, long userId) throws ServiceException;

    /**
     * Gets status of all events on which player registered
     */
    Collection<Event> status(long id);
}

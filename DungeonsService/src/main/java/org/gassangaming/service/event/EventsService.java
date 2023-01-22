package org.gassangaming.service.event;

import org.gassangaming.model.event.EventInstance;
import org.gassangaming.model.event.EventType;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.service.event.result.EventInstanceResult;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Entry points for accepting participations in events for players
 */
@Service
public interface EventsService {


    /**
     * Register player ream participation in event
     */
    @Transactional
    void register(Collection<Long> unitsIds, EventType eventType, long userId) throws ServiceException;

    /**
     * Gets status of all events on which player registered
     */
    Collection<EventInstance> status(long userId);


    /**
     * Registers server to process event instance
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    EventInstance applyServer(String host, String port, long userId) throws ServiceException;

    /**
     * Saves the result of event
     *
     * @param userId processing server user id
     */
    @Transactional
    void saveResult(EventInstanceResult result, long userId) throws ServiceException;

    /**
     * Gets event instance data to start process the event
     */
    Collection<Unit> getEventInstanceData(long eventInstanceId);

    /**
     * Cancels event registration for given user
     */
    @Transactional
    void cancel(long eventType, long userId) throws ServiceException;
}

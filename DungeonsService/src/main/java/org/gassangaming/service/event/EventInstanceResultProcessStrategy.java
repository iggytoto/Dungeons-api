package org.gassangaming.service.event;

import org.gassangaming.model.event.EventType;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

public interface EventInstanceResultProcessStrategy {

    EventType getEventType();

    @Transactional
    void process(EventInstanceResult eir) throws ServiceException;
}

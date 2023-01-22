package org.gassangaming.service.barrack;

import org.gassangaming.model.event.EventType;
import org.gassangaming.service.event.result.EventInstanceResult;
import org.gassangaming.service.event.resultprocess.EventInstanceResultProcessStrategy;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Component;

@Component
public class DefaultStrategy implements EventInstanceResultProcessStrategy {
    @Override
    public EventType getEventType() {
        return EventType.Test;
    }

    @Override
    public void process(EventInstanceResult eir) throws ServiceException {

    }
}

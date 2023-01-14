package org.gassangaming.service.barrack;

import org.gassangaming.model.event.EventType;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.event.EventInstanceResult;
import org.gassangaming.service.event.EventInstanceResultProcessStrategy;
import org.gassangaming.service.event.PhoenixRaidEventInstanceResult;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PhoenixRaidEventInstanceResultProcessStrategy implements EventInstanceResultProcessStrategy {

    @Autowired
    UnitRepository unitRepository;

    @Override
    public EventType getEventType() {
        return EventType.PhoenixRaid;
    }

    @Override
    public void process(EventInstanceResult eir) throws ServiceException {
        final var result = (PhoenixRaidEventInstanceResult) eir;
        processUnits(result.getUnitsHitPoints());
    }

    private void processUnits(Map<Long, Integer> unitsHitPoints) {

    }
}

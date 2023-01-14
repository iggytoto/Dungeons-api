package org.gassangaming.service.event;

import lombok.Data;
import org.gassangaming.model.event.EventType;

import java.util.Map;

@Data
public abstract class EventInstanceResult {
    private long eventInstanceId;
    private EventType eventType;
    private Map<Long, Integer> unitsHitPoints;
}

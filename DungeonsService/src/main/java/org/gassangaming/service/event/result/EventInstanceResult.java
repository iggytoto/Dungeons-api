package org.gassangaming.service.event.result;

import lombok.Data;
import org.gassangaming.model.event.EventType;

import java.util.Map;

@Data
public abstract class EventInstanceResult {
    private long eventInstanceId;
    private EventType eventType;
    private Map<Long, Integer> unitsHitPoints;
}

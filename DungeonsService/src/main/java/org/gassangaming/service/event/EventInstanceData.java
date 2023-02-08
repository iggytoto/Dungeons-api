package org.gassangaming.service.event;

import lombok.Builder;
import lombok.Data;
import org.gassangaming.model.event.EventType;
import org.gassangaming.model.unit.Unit;

import java.util.Collection;

@Data
@Builder
public class EventInstanceData {
    private Collection<Unit> eventParticipants;
    private long eventInstanceId;
    private EventType eventType;
}

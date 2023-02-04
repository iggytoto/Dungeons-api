package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CommonDungeonEventServiceImpl implements CommonDungeonEventService {

    @Autowired
    Collection<DungeonEventService> dungeonEventServices;

    public boolean processEvent(DungeonRoomEvent event) {
        return dungeonEventServices.stream().filter(s -> s.getTargetEventType() == event.getEventType()).findFirst().orElseThrow().process(event);
    }

    @Override
    public Collection<Long> processEvents(Collection<DungeonRoomEvent> events) {
        final var processedEventsIds = new ArrayList<Long>();
        for (var event : events.stream().filter(e -> e.getEventType() == DungeonEventType.Encounter).toList()) {
            if (processEvent(event)) {
                processedEventsIds.add(event.getId());
                return processedEventsIds;
            }
        }
        for (var event : events.stream().filter(e -> e.getEventType() == DungeonEventType.Treasure).toList()) {
            processEvent(event);
            processedEventsIds.add(event.getId());
        }
        return processedEventsIds;
    }
}

package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CommonDungeonEventService {

    boolean processEvents(DungeonRoomEvent event);

    Collection<Long> processEvents(Collection<DungeonRoomEvent> events);
}

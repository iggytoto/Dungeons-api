package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.springframework.stereotype.Service;

@Service
public interface DungeonEventService<T extends DungeonRoomEvent> {

    DungeonEventType getTargetEventType();

    boolean process(DungeonRoomEvent event);
}

package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonEvent;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.springframework.stereotype.Service;

@Service
public interface DungeonEventService<T extends DungeonEvent> {

    DungeonEventType getTargetEventType();

    boolean process(DungeonEvent event);
}

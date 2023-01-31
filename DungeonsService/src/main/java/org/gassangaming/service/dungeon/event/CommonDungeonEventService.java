package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonEvent;
import org.springframework.stereotype.Service;

@Service
public interface CommonDungeonEventService {

    boolean processEvent(DungeonEvent event);
}

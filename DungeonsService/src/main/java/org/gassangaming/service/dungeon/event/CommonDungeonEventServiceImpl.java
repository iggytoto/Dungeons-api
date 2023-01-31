package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommonDungeonEventServiceImpl implements CommonDungeonEventService {

    @Autowired
    Collection<DungeonEventService> dungeonEventServices;

    @Override
    public boolean processEvent(DungeonEvent event) {
        return dungeonEventServices.stream().filter(s -> s.getTargetEventType() == event.getEventType()).findFirst().orElseThrow().process(event);
    }
}

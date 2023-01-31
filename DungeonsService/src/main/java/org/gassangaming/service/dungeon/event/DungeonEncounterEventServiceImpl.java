package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonEvent;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.event.EncounterDungeonEvent;

public class DungeonEncounterEventServiceImpl implements DungeonEventService<EncounterDungeonEvent> {
    @Override
    public DungeonEventType getTargetEventType() {
        return DungeonEventType.Encounter;
    }

    @Override
    public boolean process(DungeonEvent event) {
        return false;
    }
}

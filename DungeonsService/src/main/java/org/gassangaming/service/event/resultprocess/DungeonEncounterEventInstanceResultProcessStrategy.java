package org.gassangaming.service.event.resultprocess;

import org.gassangaming.model.dungeon.DungeonRoomEventState;
import org.gassangaming.model.event.EventType;
import org.gassangaming.repository.dungeon.event.DungeonEncounterEventRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.event.result.DungeonEncounterEventInstanceResult;
import org.gassangaming.service.event.result.EventInstanceResult;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DungeonEncounterEventInstanceResultProcessStrategy implements EventInstanceResultProcessStrategy {

    @Autowired
    DungeonEncounterEventRepository dungeonEncounterEventRepository;
    @Autowired
    UnitRepository unitRepository;

    @Override
    public EventType getEventType() {
        return EventType.DungeonEncounter;
    }

    @Override
    public void process(EventInstanceResult eir) throws ServiceException {
        final var encounterInstanceResult = (DungeonEncounterEventInstanceResult) eir;
        final var encounterEvent = dungeonEncounterEventRepository.findById(encounterInstanceResult.getDungeonEncounterEventId()).orElseThrow();
        final var encounterUnits = encounterEvent.getUnits();
        for (var encounterUnit : encounterUnits) {
            eir.getUnitsHitPoints().remove(encounterUnit.getId());
        }
        if (encounterEvent.isRecurrent()) {
            encounterEvent.setState(DungeonRoomEventState.Active);
            dungeonEncounterEventRepository.save(encounterEvent);
        } else {
            encounterUnits.forEach(u -> unitRepository.delete(u));
            dungeonEncounterEventRepository.delete(encounterEvent);
        }
    }
}

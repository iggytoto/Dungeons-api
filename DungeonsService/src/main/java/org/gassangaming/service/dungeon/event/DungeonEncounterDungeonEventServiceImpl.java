package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.dungeon.DungeonRoomEventState;
import org.gassangaming.model.dungeon.event.EncounterDungeonRoomEvent;
import org.gassangaming.model.event.*;
import org.gassangaming.repository.dungeon.DungeonExpeditionRepository;
import org.gassangaming.repository.dungeon.DungeonRoomEventRepository;
import org.gassangaming.repository.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DungeonEncounterDungeonEventServiceImpl extends DungeonEventServiceBase implements DungeonEventService<EncounterDungeonRoomEvent> {

    @Autowired
    DungeonExpeditionRepository dungeonExpeditionRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    UserEventInstanceRepository userEventInstanceRepository;
    @Autowired
    DungeonRoomEventRepository dungeonRoomEventRepository;
    @Autowired
    UnitEventRegistrationRepository unitEventRegistrationRepository;

    @Override
    public DungeonEventType getTargetEventType() {
        return DungeonEventType.Encounter;
    }

    @Override
    public boolean process(DungeonRoomEvent roomEvent) {
        final var encounterEvent = (EncounterDungeonRoomEvent) roomEvent;
        if (rng.nextFloat() <= roomEvent.getProbability()) {
            final var room = roomEvent.getRoom();
            final var expeditions = findAllExpeditionsInRoomNotEncountered(room.getId());
            if (expeditions.isEmpty()) {
                return false;
            }
            final var event = eventRepository.findByEventType(EventType.DungeonEncounter);
            final var eventId = event.getId();
            final var eventInstance = eventInstanceRepository.save(new EventInstance(event.getId(), EventType.DungeonEncounter, EventInstanceStatus.WaitingForServer));
            for (var expedition : expeditions) {
                final var userId = expedition.getUserId();
                expedition.getRoster().forEach(u -> unitEventRegistrationRepository.save(new UnitEventRegistration(eventId, u.getId(), userId)));
                expedition.setEncountered(true);
                dungeonExpeditionRepository.saveAndFlush(expedition);
                userEventInstanceRepository.save(new UserEventInstance(expedition.getUserId(), event.getId(), eventInstance.getId()));
            }
            encounterEvent.getUnits().forEach(u -> unitEventRegistrationRepository.save(new UnitEventRegistration(eventId, u.getId(), u.getOwnerId())));
            roomEvent.setState(DungeonRoomEventState.InProgress);
            dungeonRoomEventRepository.save(roomEvent);
            return true;
        } else {
            return false;
        }
    }
}

package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.event.EncounterDungeonRoomEvent;
import org.gassangaming.model.event.*;
import org.gassangaming.repository.dungeon.DungeonExpeditionRepository;
import org.gassangaming.repository.dungeon.DungeonInstanceExpeditionLocationRepository;
import org.gassangaming.repository.event.*;
import org.gassangaming.service.event.EventsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class DungeonEncounterEventServiceImpl implements DungeonEventService<EncounterDungeonRoomEvent> {

    private final Random rng = new Random();


    @Autowired
    DungeonInstanceExpeditionLocationRepository dungeonInstanceExpeditionLocationRepository;

    @Autowired
    EventsService eventsService;
    @Autowired
    DungeonExpeditionRepository dungeonExpeditionRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    UserEventInstanceRepository userEventInstanceRepository;
    @Autowired
    UserEventRegistrationRepository userEventRegistrationRepository;
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
            final var expeditionLocations = dungeonInstanceExpeditionLocationRepository.findAllInRoomById(room.getId());
            if (expeditionLocations.isEmpty()) {
                return false;
            }
            final var event = eventRepository.findByEventType(EventType.DungeonEncounter);
            final var eventId = event.getId();
            final var eventInstance = eventInstanceRepository.save(new EventInstance(event.getId(), EventType.DungeonEncounter, EventInstanceStatus.WaitingForServer));
            for (var expeditionLocation : expeditionLocations) {
                final var expedition = expeditionLocation.getExpedition();
                final var userId = expedition.getUserId();
                userEventRegistrationRepository.save(new UserEventRegistration(userId, eventId));
                expedition.getRoster().forEach(u -> unitEventRegistrationRepository.save(new UnitEventRegistration(eventId, u.getId(), userId)));
                expedition.setEncountered(true);
                dungeonExpeditionRepository.saveAndFlush(expedition);
                userEventInstanceRepository.save(new UserEventInstance(expedition.getUserId(), event.getId(), eventInstance.getId()));
            }
            encounterEvent.getUnits().forEach(u -> unitEventRegistrationRepository.save(new UnitEventRegistration(eventId, u.getId(), u.getOwnerId())));
            return true;
        } else {
            return false;
        }
    }
}

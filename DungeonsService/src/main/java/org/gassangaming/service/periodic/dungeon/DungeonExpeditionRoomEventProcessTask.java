package org.gassangaming.service.periodic.dungeon;

import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation;
import org.gassangaming.repository.dungeon.DungeonInstanceExpeditionLocationRepository;
import org.gassangaming.repository.dungeon.DungeonRoomRepository;
import org.gassangaming.service.dungeon.event.CommonDungeonEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DungeonExpeditionRoomEventProcessTask {
    private final static long FIVE_MINUTES = 1000 * 60 * 5;

    @Autowired
    DungeonInstanceExpeditionLocationRepository dungeonInstanceExpeditionLocationRepository;
    @Autowired
    DungeonRoomRepository dungeonRoomRepository;
    @Autowired
    CommonDungeonEventService commonDungeonEventService;

    @Transactional
    @Scheduled(fixedRate = FIVE_MINUTES)
    public void scheduleRoomEvents() {
        dungeonInstanceExpeditionLocationRepository.findAllInRoom().forEach(this::processLocation);
    }

    private void processLocation(DungeonInstanceExpeditionLocation dungeonInstanceExpeditionLocation) {
        final var room = dungeonRoomRepository.findById(dungeonInstanceExpeditionLocation.getLocationId()).orElseThrow();
        final var events = room.getEvents();
        for (var event : events.stream().filter(e -> e.getEventType() == DungeonEventType.Encounter).toList()) {
            if (commonDungeonEventService.processEvent(event)) {
                break;
            }
        }
        for (var event : events.stream().filter(e -> e.getEventType() == DungeonEventType.Treasure).toList()) {
            commonDungeonEventService.processEvent(event);
        }
    }
}

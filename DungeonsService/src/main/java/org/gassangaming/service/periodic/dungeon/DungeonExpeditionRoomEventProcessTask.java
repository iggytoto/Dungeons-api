package org.gassangaming.service.periodic.dungeon;

import org.gassangaming.model.dungeon.DungeonRoom;
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
    DungeonRoomRepository dungeonRoomRepository;
    @Autowired
    CommonDungeonEventService commonDungeonEventService;

    @Transactional
    @Scheduled(fixedRate = FIVE_MINUTES)
    public void scheduleRoomEvents() {
        dungeonRoomRepository.findAllWithExpeditions().forEach(this::processRoom);
    }

    private void processRoom(DungeonRoom room) {
        if (!room.getDungeonRoomEvents().isEmpty()) {
            commonDungeonEventService.processEvents(room.getDungeonRoomEvents());
        }
    }
}

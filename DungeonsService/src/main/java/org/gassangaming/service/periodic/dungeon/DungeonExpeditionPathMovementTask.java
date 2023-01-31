package org.gassangaming.service.periodic.dungeon;

import org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.dungeon.DungeonInstanceExpeditionLocationRepository;
import org.gassangaming.repository.dungeon.DungeonPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class DungeonExpeditionPathMovementTask {

    @Autowired
    DungeonInstanceExpeditionLocationRepository dungeonInstanceExpeditionLocationRepository;
    @Autowired
    DungeonPathRepository dungeonPathRepository;

    private final static long FIVE_MINUTES = 1000 * 60 * 5;

    @Transactional
    @Scheduled(fixedRate = FIVE_MINUTES)
    public void scheduleExpeditionsMovement() {
        dungeonInstanceExpeditionLocationRepository.findAllOnPath().forEach(this::processLocation);
    }

    private void processLocation(DungeonInstanceExpeditionLocation dungeonInstanceExpeditionLocation) {
        final var path = dungeonPathRepository.findById(dungeonInstanceExpeditionLocation.getLocationId()).orElseThrow();
        final var rosterSpeed = calculateRosterSpeed(dungeonInstanceExpeditionLocation.getExpedition().getRoster());
        final var timeDiff = new Date().getTime() - dungeonInstanceExpeditionLocation.getLocationEnteredTimestamp().getTime();
        final var distanceTravelled = rosterSpeed * timeDiff;
        if (distanceTravelled >= path.getDistance()) {
            dungeonInstanceExpeditionLocation.setLocationId(path.getToRoomId());
            dungeonInstanceExpeditionLocation.setRoom(true);
            dungeonInstanceExpeditionLocation.setLocationEnteredTimestamp(new Date());
            dungeonInstanceExpeditionLocationRepository.save(dungeonInstanceExpeditionLocation);
        }
    }

    /**
     * @return roster travel speed meters per millisecond
     */
    private float calculateRosterSpeed(List<Unit> roster) {
        return roster.stream().map(Unit::getMovementSpeed).min(Double::compare).orElse(0f) / 4000;
    }
}

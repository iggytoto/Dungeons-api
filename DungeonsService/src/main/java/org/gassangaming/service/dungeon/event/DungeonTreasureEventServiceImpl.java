package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.DungeonExpeditionItem;
import org.gassangaming.model.dungeon.event.TreasureDungeonRoomEvent;
import org.gassangaming.repository.dungeon.DungeonExpeditionItemRepository;
import org.gassangaming.repository.dungeon.DungeonInstanceExpeditionLocationRepository;
import org.gassangaming.repository.dungeon.event.DungeonTreasureEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DungeonTreasureEventServiceImpl implements DungeonEventService<TreasureDungeonRoomEvent> {

    @Autowired
    DungeonExpeditionItemRepository dungeonExpeditionItemRepository;
    @Autowired
    DungeonTreasureEventRepository treasureEventRepository;
    @Autowired
    DungeonInstanceExpeditionLocationRepository dungeonInstanceExpeditionLocationRepository;

    private final Random rng = new Random();

    @Override
    public DungeonEventType getTargetEventType() {
        return DungeonEventType.Treasure;
    }

    @Override
    public boolean process(DungeonRoomEvent event) {
        if (rng.nextFloat() >= event.getProbability()) {
            final var treasureEvent = (TreasureDungeonRoomEvent) event;
            final var expeditionLocations = dungeonInstanceExpeditionLocationRepository.findAllInRoomById(treasureEvent.getRoom().getId());
            final var winnerExpedition = expeditionLocations.get(rng.nextInt(expeditionLocations.size()));
            dungeonExpeditionItemRepository.save(new DungeonExpeditionItem(winnerExpedition.getId(), treasureEvent.getItemId()));
            treasureEventRepository.delete(treasureEvent);
            return true;
        } else {
            return false;
        }
    }
}

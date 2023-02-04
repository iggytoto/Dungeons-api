package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.DungeonExpeditionItem;
import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.dungeon.event.TreasureDungeonRoomEvent;
import org.gassangaming.repository.dungeon.DungeonExpeditionItemRepository;
import org.gassangaming.repository.dungeon.event.DungeonTreasureEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DungeonTreasureDungeonEventServiceImpl extends DungeonEventServiceBase implements DungeonEventService<TreasureDungeonRoomEvent> {

    @Autowired
    DungeonExpeditionItemRepository dungeonExpeditionItemRepository;
    @Autowired
    DungeonTreasureEventRepository treasureEventRepository;

    private final Random rng = new Random();

    @Override
    public DungeonEventType getTargetEventType() {
        return DungeonEventType.Treasure;
    }

    @Override
    public boolean process(DungeonRoomEvent event) {
        if (rng.nextFloat() <= event.getProbability()) {
            final var treasureEvent = (TreasureDungeonRoomEvent) event;
            final var expeditions = findAllExpeditionsInRoomNotEncountered(event.getRoomId());
            final var winnerExpedition = expeditions.size() == 1 ? expeditions.get(0) : expeditions.get(rng.nextInt(expeditions.size()));
            dungeonExpeditionItemRepository.save(new DungeonExpeditionItem(winnerExpedition.getId(), treasureEvent.getItemId()));
            treasureEventRepository.delete(treasureEvent);
            return true;
        } else {
            return false;
        }
    }
}

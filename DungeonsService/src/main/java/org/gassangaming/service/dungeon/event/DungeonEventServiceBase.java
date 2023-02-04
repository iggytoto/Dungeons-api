package org.gassangaming.service.dungeon.event;

import org.gassangaming.model.dungeon.DungeonExpedition;
import org.gassangaming.repository.dungeon.DungeonExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;


public abstract class DungeonEventServiceBase {

    protected final Random rng = new Random();
    @Autowired
    DungeonExpeditionRepository dungeonExpeditionRepository;

    protected List<DungeonExpedition> findAllExpeditionsInRoomNotEncountered(long roomId) {
        return dungeonExpeditionRepository.findAllByRoomIdAndNotEncountered(roomId);
    }
}

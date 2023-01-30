package org.gassangaming.service.dungeon;

import org.gassangaming.model.dungeon.DungeonExpedition;
import org.gassangaming.model.dungeon.DungeonInstance;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface DungeonService {

    /**
     * Gets the list of currently active dungeons.
     */
    Collection<DungeonInstance> getAllActiveDungeons();

    /**
     * Registers set of players units to explore dungeon instance as expedition
     */
    DungeonExpedition createExpedition(Collection<Long> roster, long startingRoomId, long dungeonInstanceId, long userId);

    /**
     * Request returning expedition to town.
     *
     * @throws ServiceException in case expedition currently cannot return to town.
     */
    void returnExpeditionToTown(long expeditionId, long userId) throws ServiceException;

    /**
     * Order expedition to move to target room
     */
    void moveExpeditionToRoom(long targetRoomId, long expeditionId, long userId);
}

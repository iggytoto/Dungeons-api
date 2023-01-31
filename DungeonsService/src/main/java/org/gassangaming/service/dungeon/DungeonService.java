package org.gassangaming.service.dungeon;

import org.gassangaming.model.dungeon.DungeonExpedition;
import org.gassangaming.model.dungeon.DungeonInstance;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    DungeonExpedition createExpedition(Collection<Long> roster, long startingRoomId, long dungeonInstanceId, long userId) throws ServiceException;

    /**
     * Request returning expedition to town.
     *
     * @throws ServiceException in case expedition currently cannot return to town.
     */
    @Transactional
    void returnExpeditionToTown(long expeditionId, long userId) throws ServiceException;

    /**
     * Order expedition to move to target room
     */
    @Transactional
    void moveExpeditionToRoom(long targetRoomId, long expeditionId, long userId) throws ServiceException;
}

package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonExpedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface DungeonExpeditionRepository extends JpaRepository<DungeonExpedition, Long> {

    String FIND_ALL_BY_ROOM_ID_AND_NOT_ENCOUNTERED = "SELECT e FROM DungeonExpedition e JOIN DungeonInstanceExpeditionLocation l ON e.id = l.expeditionId WHERE e.isEncountered = false AND l.isRoom = true AND l.locationId=:" + PARAM_1;

    @Query(value = FIND_ALL_BY_ROOM_ID_AND_NOT_ENCOUNTERED)
    List<DungeonExpedition> findAllByRoomIdAndNotEncountered(@Param(PARAM_1) long roomId);
}

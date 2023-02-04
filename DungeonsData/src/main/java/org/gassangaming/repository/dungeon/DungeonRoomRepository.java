package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DungeonRoomRepository extends JpaRepository<DungeonRoom, Long> {

    String FIND_ALL_WITH_EXPEDITIONS = "SELECT DISTINCT r FROM DungeonRoom r JOIN DungeonInstanceExpeditionLocation l ON r.id = l.locationId WHERE l.id is not null AND l.isRoom = true";

    @Query(value = FIND_ALL_WITH_EXPEDITIONS)
    List<DungeonRoom> findAllWithExpeditions();
}

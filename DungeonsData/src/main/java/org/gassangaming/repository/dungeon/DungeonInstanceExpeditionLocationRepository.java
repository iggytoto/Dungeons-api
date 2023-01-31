package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface DungeonInstanceExpeditionLocationRepository extends JpaRepository<DungeonInstanceExpeditionLocation, Long> {

    String FIND_ALL_ON_PATH = "SELECT l FROM DungeonInstanceExpeditionLocation l WHERE l.isRoom = false";
    String FIND_ALL_IN_ROOM = "SELECT l FROM DungeonInstanceExpeditionLocation l WHERE l.isRoom = true";
    String FIND_ALL_IN_ROOM_BY_ID = "SELECT l FROM DungeonInstanceExpeditionLocation l WHERE l.isRoom = true AND l.locationId=:" + PARAM_1;

    @Query(value = FIND_ALL_ON_PATH)
    List<DungeonInstanceExpeditionLocation> findAllOnPath();

    @Query(value = FIND_ALL_IN_ROOM)
    List<DungeonInstanceExpeditionLocation> findAllInRoom();

    @Query(value = FIND_ALL_IN_ROOM_BY_ID)
    List<DungeonInstanceExpeditionLocation> findAllInRoomById(@Param(PARAM_1) long id);
}

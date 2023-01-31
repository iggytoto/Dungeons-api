package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DungeonInstanceExpeditionLocationRepository extends JpaRepository<DungeonInstanceExpeditionLocation, Long> {

    String FIND_ALL_ON_PATH = "SELECT l FROM DungeonInstanceExpeditionLocation l WHERE l.isRoom = false";

    @Query(value = FIND_ALL_ON_PATH)
    Collection<DungeonInstanceExpeditionLocation> findAllOnPath();
}

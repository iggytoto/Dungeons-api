package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DungeonInstanceExpeditionLocationRepository extends JpaRepository<DungeonInstanceExpeditionLocation, DungeonInstanceExpeditionLocation.DungeonInstanceExpeditionLocationId> {
    Optional<DungeonInstanceExpeditionLocation> findByExpeditionId(long expeditionId);
}

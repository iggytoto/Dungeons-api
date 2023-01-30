package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonPathRepository extends JpaRepository<DungeonPath, DungeonPath.DungeonPathId> {
}

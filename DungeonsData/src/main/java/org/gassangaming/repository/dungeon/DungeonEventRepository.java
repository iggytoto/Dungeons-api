package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonEventRepository extends JpaRepository<DungeonEvent, Long> {
}

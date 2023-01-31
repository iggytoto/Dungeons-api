package org.gassangaming.repository.dungeon.event;

import org.gassangaming.model.dungeon.event.TreasureDungeonEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonTreasureEventRepository extends JpaRepository<TreasureDungeonEvent, Long> {
}

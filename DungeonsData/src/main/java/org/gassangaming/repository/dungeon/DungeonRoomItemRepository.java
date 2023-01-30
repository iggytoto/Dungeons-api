package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonRoomItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonRoomItemRepository extends JpaRepository<DungeonRoomItem, DungeonRoomItem.DungeonRoomItemId> {
}

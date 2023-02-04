package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonExpeditionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DungeonExpeditionItemRepository extends JpaRepository<DungeonExpeditionItem, DungeonExpeditionItem.DungeonExpeditionItemId> {
    List<DungeonExpeditionItem> findAllByExpeditionId(long expeditionId);
}

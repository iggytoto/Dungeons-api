package org.gassangaming.repository.dungeon;

import org.gassangaming.model.dungeon.DungeonExpeditionUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DungeonExpeditionUnitsRepository extends JpaRepository<DungeonExpeditionUnit, DungeonExpeditionUnit.DungeonExpeditionUnitsId> {
    Collection<DungeonExpeditionUnit> findAllByExpeditionId(long expeditionId);
}

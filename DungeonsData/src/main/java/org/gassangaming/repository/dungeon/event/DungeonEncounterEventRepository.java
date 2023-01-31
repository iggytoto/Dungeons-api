package org.gassangaming.repository.dungeon.event;

import org.gassangaming.model.dungeon.event.EncounterDungeonEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonEncounterEventRepository extends JpaRepository<EncounterDungeonEvent, Long> {
}

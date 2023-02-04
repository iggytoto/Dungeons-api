package org.gassangaming.repository.dungeon.event;

import org.gassangaming.model.dungeon.event.EncounterDungeonRoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonEncounterEventRepository extends JpaRepository<EncounterDungeonRoomEvent, Long> {
}

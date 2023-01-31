package org.gassangaming.model.dungeon.event;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.dungeon.DungeonEvent;

import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.dungeon.event.EncounterDungeonEvent.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class EncounterDungeonEvent extends DungeonEvent {

    public static final String TABLE_NAME = "dungeon_event_encounters";
}

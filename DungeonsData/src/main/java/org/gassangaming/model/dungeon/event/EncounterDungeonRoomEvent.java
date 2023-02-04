package org.gassangaming.model.dungeon.event;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.dungeon.DungeonRoomEvent;
import org.gassangaming.model.unit.Unit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.gassangaming.model.dungeon.event.EncounterDungeonRoomEvent.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class EncounterDungeonRoomEvent extends DungeonRoomEvent {

    public static final String TABLE_NAME = "dungeon_event_encounters";

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = EncounterDungeonEventUnit.TABLE_NAME, joinColumns = @JoinColumn(name = EncounterDungeonEventUnit.ENCOUNTER_EVENT_ID), inverseJoinColumns = @JoinColumn(name = EncounterDungeonEventUnit.UNIT_ID_COLUMN_NAME))
    Collection<Unit> units = new ArrayList<>();

}

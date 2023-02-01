package org.gassangaming.model.dungeon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.event.EncounterDungeonEventUnit.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(EncounterDungeonEventUnit.EncounterDungeonEventUnitId.class)
public class EncounterDungeonEventUnit {

    public static final String TABLE_NAME = "dungeon_event_encounters_units";
    public static final String ENCOUNTER_EVENT_ID = "encounter_event_id";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;

    protected EncounterDungeonEventUnit() {
    }

    public EncounterDungeonEventUnit(long encounterEventId, long unitId) {
        this.encounterEventId = encounterEventId;
        this.unitId = unitId;
    }

    @Id
    @Column(name = ENCOUNTER_EVENT_ID)
    private long encounterEventId;

    @Id
    @Column(name = UNIT_ID_COLUMN_NAME)
    private long unitId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class EncounterDungeonEventUnitId implements Serializable {
        private long encounterEventId;
        private long unitId;
    }
}

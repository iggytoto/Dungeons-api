package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonExpeditionUnit.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonExpeditionUnit.DungeonExpeditionUnitsId.class)
public class DungeonExpeditionUnit {

    public static final String TABLE_NAME = "dungeon_expedition_units";
    public static final String EXPEDITION_ID_COLUMN_NAME = "expedition_id";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;

    protected DungeonExpeditionUnit() {
    }

    public DungeonExpeditionUnit(long expeditionId, long unitId) {
        this.expeditionId = expeditionId;
        this.unitId = unitId;
    }

    @Id
    @Column(name = EXPEDITION_ID_COLUMN_NAME)
    private long expeditionId;

    @Id
    @Column(name = UNIT_ID_COLUMN_NAME)
    private long unitId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DungeonExpeditionUnitsId implements Serializable {
        private long expeditionId;
        private long unitId;
    }
}

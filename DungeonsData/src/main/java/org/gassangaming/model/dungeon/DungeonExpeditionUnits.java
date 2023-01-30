package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonExpeditionUnits.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonExpeditionUnits.DungeonExpeditionUnitsId.class)
public class DungeonExpeditionUnits {

    public static final String TABLE_NAME = "dungeon_expedition_units";
    public static final String EXPEDITION_ID_COLUMN_NAME = "expedition_id";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;

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

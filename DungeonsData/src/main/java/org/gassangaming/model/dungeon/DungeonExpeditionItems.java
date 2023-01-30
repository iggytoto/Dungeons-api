package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonExpeditionItems.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonExpeditionItems.DungeonExpeditionItemsId.class)
public class DungeonExpeditionItems {

    public static final String TABLE_NAME = "dungeon_expedition_items";
    public static final String EXPEDITION_ID_COLUMN_NAME = "expedition_id";
    public static final String ITEM_ID_COLUMN_NAME = Constants.ITEM_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column(name = EXPEDITION_ID_COLUMN_NAME)
    private long expeditionId;

    @Id
    @Column(name = ITEM_ID_COLUMN_NAME)
    private long item_id;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DungeonExpeditionItemsId implements Serializable {
        private long expeditionId;
        private long itemId;
    }
}

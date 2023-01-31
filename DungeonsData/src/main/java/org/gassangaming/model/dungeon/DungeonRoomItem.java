package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonRoomItem.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonRoomItem.DungeonRoomItemId.class)
public class DungeonRoomItem {

    public static final String TABLE_NAME = "dungeon_room_items";
    public static final String ROOM_ID_COLUMN_NAME = "room_id";
    public static final String ITEM_ID_COLUMN_NAME = Constants.ITEM_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column(name = ROOM_ID_COLUMN_NAME)
    private long roomId;
    @Id
    @Column(name = ITEM_ID_COLUMN_NAME)
    private long itemId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DungeonRoomItemId implements Serializable {
        private long roomId;
        private long itemId;
    }
}

package org.gassangaming.model.dungeon.event;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;
import org.gassangaming.model.dungeon.DungeonEventType;
import org.gassangaming.model.dungeon.DungeonRoom;
import org.gassangaming.model.dungeon.DungeonRoomEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.dungeon.event.TreasureDungeonRoomEvent.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class TreasureDungeonRoomEvent extends DungeonRoomEvent {

    public static final String TABLE_NAME = "dungeon_event_treasure";
    public static final String ITEM_ID_COLUMN_NAME = Constants.ITEM_ID_FOREIGN_KEY_COLUMN_NAME;

    protected TreasureDungeonRoomEvent() {
    }

    public TreasureDungeonRoomEvent(long itemId, DungeonRoom room, String description, float probability, boolean recurrent) {
        this.setRoom(room);
        this.setEventType(DungeonEventType.Treasure);
        this.setItemId(itemId);
        this.setDescription(description);
        this.setProbability(probability);
        this.setRecurrent(recurrent);
    }

    @Column(name = ITEM_ID_COLUMN_NAME)
    private long itemId;

}

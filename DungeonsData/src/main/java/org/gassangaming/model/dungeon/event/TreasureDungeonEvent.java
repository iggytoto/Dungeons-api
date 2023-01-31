package org.gassangaming.model.dungeon.event;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;
import org.gassangaming.model.dungeon.DungeonEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.gassangaming.model.dungeon.event.TreasureDungeonEvent.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class TreasureDungeonEvent extends DungeonEvent {

    public static final String TABLE_NAME = "dungeon_event_treasure";
    public static final String ITEM_ID_COLUMN_NAME = Constants.ITEM_ID_FOREIGN_KEY_COLUMN_NAME;

    @Column(name = ITEM_ID_COLUMN_NAME)
    private long itemId;

}

package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonRoomEvent.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonRoomEvent.DungeonRoomEventId.class)
public class DungeonRoomEvent {

    public static final String TABLE_NAME = "dungeon_rooms_events";
    public static final String ROOM_ID_COLUMN_NAME = "room_id";
    public static final String EVENT_ID_COLUMN_NAME = "dungeon_event_id";

    @Id
    @Column(name = ROOM_ID_COLUMN_NAME)
    private long roomId;

    @Id
    @Column(name = EVENT_ID_COLUMN_NAME)
    private long eventId;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DungeonRoomEventId implements Serializable {
        private long roomId;
        private long eventId;
    }
}

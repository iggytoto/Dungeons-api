package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DungeonRoomEvent {

    public static final String SEQUENCE_NAME = "s_dungeon_event_id";
    public static final String EVENT_TYPE_COLUMN_NAME = "event_type";
    public static final String PROBABILITY_COLUMN_NAME = "probability";
    public static final String IS_RECURRENT_COLUMN_NAME = "is_recurrent";
    public static final String DESCRIPTION_COLUMN_NAME = "description";
    public static final String ROOM_ID_COLUMN_NAME = "room_id";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = EVENT_TYPE_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private DungeonEventType eventType;

    @Column(name = PROBABILITY_COLUMN_NAME)
    private float probability;

    @Column(name = IS_RECURRENT_COLUMN_NAME)
    private boolean isRecurrent;

    @Column(name = DESCRIPTION_COLUMN_NAME)
    private String description;

    @Column(name = ROOM_ID_COLUMN_NAME, updatable = false, insertable = false)
    private long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ROOM_ID_COLUMN_NAME)
    private DungeonRoom room;

    public void setRoom(DungeonRoom room) {
        if (room == null) {
            throw new IllegalStateException();
        } else {
            this.room = room;
            this.roomId = room.getId();
        }
    }
}

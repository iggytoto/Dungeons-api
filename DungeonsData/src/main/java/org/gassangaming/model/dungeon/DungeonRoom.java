package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.gassangaming.model.dungeon.DungeonRoom.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class DungeonRoom {

    public static final String SEQUENCE_NAME = "s_dungeon_rooms_id";

    public static final String TABLE_NAME = "dungeon_rooms";
    public static final String ENTRANCE_FLAG_COLUMN_NAME = "is_entrance";
    public static final String DUNGEON_INSTANCE_ID_COLUMN_NAME = "dungeon_instance_id";
    public static final String DESCRIPTION_COLUMN_NAME = "description";


    public DungeonRoom() {
    }

    public DungeonRoom(boolean isEntrance, DungeonInstance dungeonInstance) {
        this.isEntrance = isEntrance;
        this.dungeonInstanceId = dungeonInstance.getId();
        this.dungeonInstance = dungeonInstance;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = ENTRANCE_FLAG_COLUMN_NAME)
    private boolean isEntrance;

    @Column(name = DUNGEON_INSTANCE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private long dungeonInstanceId;

    @Column(name = DESCRIPTION_COLUMN_NAME)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = DungeonRoomItem.TABLE_NAME, joinColumns = @JoinColumn(name = DungeonRoomItem.ROOM_ID_COLUMN_NAME), inverseJoinColumns = @JoinColumn(name = DungeonRoomItem.ITEM_ID_COLUMN_NAME))
    Collection<Item> items = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = DungeonRoomEvent.TABLE_NAME, joinColumns = @JoinColumn(name = DungeonRoomEvent.ROOM_ID_COLUMN_NAME), inverseJoinColumns = @JoinColumn(name = DungeonRoomEvent.EVENT_ID_COLUMN_NAME))
    Collection<DungeonEvent> events = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = DUNGEON_INSTANCE_ID_COLUMN_NAME)
    private DungeonInstance dungeonInstance;

}

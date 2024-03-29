package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.gassangaming.model.dungeon.DungeonPath.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class DungeonPath {

    public static final String TABLE_NAME = "dungeon_paths";
    public static final String SEQUENCE_NAME = "s_dungeon_path_id";

    public static final String ROOM_ONE_ID_COLUMN_NAME = "room_one_id";
    public static final String ROOM_TWO_ID_COLUMN_NAME = "room_two_id";
    public static final String DUNGEON_INSTANCE_ID_COLUMN_NAME = "dungeon_instance_id";
    public static final String DISTANCE_COLUMN_NAME = "distance";
    public static final String DESCRIPTION_COLUMN_NAME = "description";

    public DungeonPath() {
    }

    public DungeonPath(long fromRoomId, long toRoomId, DungeonInstance dungeonInstance, float distance) {
        this.fromRoomId = fromRoomId;
        this.toRoomId = toRoomId;
        this.dungeonInstanceId = dungeonInstance.getId();
        this.dungeonInstance = dungeonInstance;
        this.distance = distance;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = ROOM_ONE_ID_COLUMN_NAME)
    private long fromRoomId;

    @Column(name = ROOM_TWO_ID_COLUMN_NAME)
    private long toRoomId;

    @Column(name = DUNGEON_INSTANCE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private long dungeonInstanceId;

    @Column(name = DISTANCE_COLUMN_NAME)
    private float distance;

    @Column(name = DESCRIPTION_COLUMN_NAME)
    private String description;

    @ManyToOne
    @JoinColumn(name = DUNGEON_INSTANCE_ID_COLUMN_NAME)
    private DungeonInstance dungeonInstance;

}

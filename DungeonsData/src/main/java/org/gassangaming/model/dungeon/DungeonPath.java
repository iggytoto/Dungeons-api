package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonPath.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonPath.DungeonPathId.class)
public class DungeonPath {

    public static final String TABLE_NAME = "dungeon_paths";

    public static final String ROOM_ONE_ID_COLUMN_NAME = "room_one_id";
    public static final String ROOM_TWO_ID_COLUMN_NAME = "room_two_id";
    public static final String DUNGEON_INSTANCE_COLUMN_NAME = "dungeon_instance_id";


    @Id
    @Column(name = ROOM_ONE_ID_COLUMN_NAME)
    private long roomOneId;

    @Id
    @Column(name = ROOM_TWO_ID_COLUMN_NAME)
    private long roomTwoId;

    @Column(name = DUNGEON_INSTANCE_COLUMN_NAME)
    private long dungeonInstanceId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DungeonPathId implements Serializable {
        protected long roomOneId;
        protected long roomTwoId;
    }

}

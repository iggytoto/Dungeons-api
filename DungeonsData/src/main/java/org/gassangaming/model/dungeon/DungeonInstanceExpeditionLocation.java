package org.gassangaming.model.dungeon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(DungeonInstanceExpeditionLocation.DungeonInstanceExpeditionLocationId.class)
public class DungeonInstanceExpeditionLocation {
    public static final String TABLE_NAME = "dungeon_instance_expedition_location";
    public static final String EXPEDITION_ID_COLUMN_NAME = "expedition_id";
    public static final String DUNGEON_INSTANCE_ID_COLUMN_NAME = "dungeon_instance_id";
    public static final String LOCATION_ID_COLUMN_NAME = "location_id";
    public static final String IS_ROOM_COLUMN_NAME = "is_room";

    protected DungeonInstanceExpeditionLocation() {
    }

    public DungeonInstanceExpeditionLocation(long expeditionId, long dungeonInstanceId, long locationId, boolean isRoom) {
        this.expeditionId = expeditionId;
        this.dungeonInstanceId = dungeonInstanceId;
        this.locationId = locationId;
        this.isRoom = isRoom;
    }

    @Id
    @Column
    private long expeditionId;

    @Id
    @Column
    private long dungeonInstanceId;

    @Column
    private long locationId;

    @Column
    private boolean isRoom;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DungeonInstanceExpeditionLocationId implements Serializable {
        long expeditionId;
        long dungeonInstanceId;
    }
}

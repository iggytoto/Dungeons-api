package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.gassangaming.model.dungeon.DungeonInstanceExpeditionLocation.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class DungeonInstanceExpeditionLocation {

    public static final String TABLE_NAME = "dungeon_instance_expedition_location";
    public static final String EXPEDITION_ID_COLUMN_NAME = "expedition_id";
    public static final String DUNGEON_INSTANCE_ID_COLUMN_NAME = "dungeon_instance_id";
    public static final String LOCATION_ID_COLUMN_NAME = "location_id";
    public static final String IS_ROOM_COLUMN_NAME = "is_room";

    protected DungeonInstanceExpeditionLocation() {
    }

    public DungeonInstanceExpeditionLocation(DungeonExpedition expedition, long dungeonInstanceId, long locationId, boolean isRoom) {
        this.id = expedition.getId();
        this.expeditionId = expedition.getId();
        this.dungeonInstanceId = dungeonInstanceId;
        this.locationId = locationId;
        this.isRoom = isRoom;
        this.expedition = expedition;
    }

    @Id
    private long id;

    @Column(name = EXPEDITION_ID_COLUMN_NAME, insertable = false, updatable = false)
    private long expeditionId;

    @Column(name = DUNGEON_INSTANCE_ID_COLUMN_NAME)
    private long dungeonInstanceId;

    @Column(name = LOCATION_ID_COLUMN_NAME)
    private long locationId;

    @Column(name = IS_ROOM_COLUMN_NAME)
    private boolean isRoom;

    @OneToOne
    @JoinColumn(name = EXPEDITION_ID_COLUMN_NAME)
    private DungeonExpedition expedition;

}

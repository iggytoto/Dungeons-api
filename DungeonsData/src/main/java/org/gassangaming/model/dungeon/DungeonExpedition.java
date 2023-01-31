package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;

import static org.gassangaming.model.dungeon.DungeonExpedition.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class DungeonExpedition {

    public static final String SEQUENCE_NAME = "s_dungeon_expedition_id";

    public static final String TABLE_NAME = "dungeon_expeditions";
    public static final String DUNGEON_INSTANCE_ID_COLUMN_NAME = "dungeon_instance_id";
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;


    public DungeonExpedition(){}
    public DungeonExpedition(long dungeonInstanceId, long userId) {
        this.dungeonInstanceId = dungeonInstanceId;
        this.userId = userId;
    }


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = DUNGEON_INSTANCE_ID_COLUMN_NAME)
    private long dungeonInstanceId;

    @Column(name = USER_ID_COLUMN_NAME)
    private long userId;

}

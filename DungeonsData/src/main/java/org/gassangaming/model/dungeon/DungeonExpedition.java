package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;
import org.gassangaming.model.unit.Unit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    public DungeonExpedition() {
    }

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

    @ManyToMany
    @JoinTable(name = DungeonExpeditionUnit.TABLE_NAME, joinColumns = @JoinColumn(name = DungeonExpeditionUnit.EXPEDITION_ID_COLUMN_NAME), inverseJoinColumns = @JoinColumn(name = DungeonExpeditionUnit.UNIT_ID_COLUMN_NAME))
    private List<Unit> roster = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = DungeonExpeditionItem.TABLE_NAME, joinColumns = @JoinColumn(name = DungeonExpeditionItem.EXPEDITION_ID_COLUMN_NAME), inverseJoinColumns = @JoinColumn(name = DungeonExpeditionItem.ITEM_ID_COLUMN_NAME))
    private List<Unit> loot = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "expedition")
    private DungeonInstanceExpeditionLocation location;

}

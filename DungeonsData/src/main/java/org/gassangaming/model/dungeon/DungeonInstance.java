package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.gassangaming.model.dungeon.DungeonInstance.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class DungeonInstance {

    public static final String SEQUENCE_NAME = "s_dungeon_instances_id";

    public static final String TABLE_NAME = "dungeon_instances";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dungeonInstance")
    private Collection<DungeonRoom> rooms = new ArrayList<>();


}

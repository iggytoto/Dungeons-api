package org.gassangaming.model.dungeon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.gassangaming.model.dungeon.DungeonEvent.TABLE_NAME;


@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class DungeonEvent {

    public static final String SEQUENCE_NAME = "s_dungeon_event_id";
    public static final String TABLE_NAME = "dungeon_event";
    public static final String EVENT_TYPE_COLUMN_NAME = "event_type";
    public static final String PROBABILITY_COLUMN_NAME = "probability";

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
}

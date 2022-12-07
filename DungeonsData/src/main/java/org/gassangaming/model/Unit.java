package org.gassangaming.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

import static org.gassangaming.model.Unit.TABLE_NAME;

@Table(name = TABLE_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@DiscriminatorValue("Unit")
public class Unit implements Serializable {

    public static final String TABLE_NAME = "units";
    public static final String OWNER_ID_COLUMN_NAME = "owner_id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String HIT_POINTS_COLUMN_NAME = "hp";
    public static final String ARMOR_COLUMN_NAME = "armor";
    public static final String MAGIC_RESIST_COLUMN_NAME = "mr";
    public static final String DAMAGE_COLUMN_NAME = "dmg";
    public static final String TRAINING_EXPERIENCE_COLUMN_NAME = "training_exp";
    public static final String ACTIVITY_COLUMN_NAME = "activity";
    public static final String SEQUENCE_NAME = "s_units_id";


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    protected Long id;

    @Column(name = NAME_COLUMN_NAME)
    private String name;

    @Column(name = OWNER_ID_COLUMN_NAME)
    private Long ownerId;
    @Column(name = HIT_POINTS_COLUMN_NAME)
    private int hitPoints;
    @Column(name = ARMOR_COLUMN_NAME)
    private int armor;
    @Column(name = MAGIC_RESIST_COLUMN_NAME)
    private int magicResistance;
    @Column(name = DAMAGE_COLUMN_NAME)
    private int damage;
    @Column(name = TRAINING_EXPERIENCE_COLUMN_NAME)
    private int trainingExperience;
    @Column(name = ACTIVITY_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private Activity activity;

}

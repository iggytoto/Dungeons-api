package org.gassangaming.model.unit;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Valuable;
import org.gassangaming.model.item.EquippedItem;
import org.gassangaming.model.item.Item;
import org.gassangaming.model.skills.UnitSkills;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.gassangaming.model.unit.Unit.TABLE_NAME;
import static org.gassangaming.model.unit.Unit.UNIT_TYPE_COLUMN_NAME;

@Table(name = TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Getter
@Setter
@DiscriminatorColumn(name = UNIT_TYPE_COLUMN_NAME)
public class Unit {

    public static final String TABLE_NAME = "units";
    public static final String OWNER_ID_COLUMN_NAME = "owner_id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String HIT_POINTS_COLUMN_NAME = "hp";
    public static final String MAX_HIT_POINTS_COLUMN_NAME = "max_hp";
    public static final String MANA_COLUMN_NAME = "mana";
    public static final String MAX_MANA_POINTS_COLUMN_NAME = "max_mana";
    public static final String ARMOR_COLUMN_NAME = "armor";
    public static final String MAGIC_RESIST_COLUMN_NAME = "mr";
    public static final String DAMAGE_COLUMN_NAME = "dmg";
    public static final String ATTACK_SPEED_COLUMN_NAME = "atk_spd";
    public static final String ACTIVITY_COLUMN_NAME = "activity";
    public static final String ATTACK_RANGE_COLUMN_NAME = "attack_range";
    public static final String MOVEMENT_SPEED_COLUMN_NAME = "movement_speed";
    public static final String BATTLE_BEHAVIOR_COLUMN_NAME = "battle_behavior";
    public static final String UNIT_TYPE_COLUMN_NAME = "unit_type";
    public static final String SEQUENCE_NAME = "s_units_id";


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    protected long id;

    @Column(name = NAME_COLUMN_NAME)
    protected String name;
    @Column(name = OWNER_ID_COLUMN_NAME)
    protected Long ownerId;
    @Column(name = HIT_POINTS_COLUMN_NAME)
    protected int hitPoints;
    @Column(name = MAX_HIT_POINTS_COLUMN_NAME)
    protected int maxHitPoints;
    @Column(name = MANA_COLUMN_NAME)
    protected int mana;
    @Column(name = MAX_MANA_POINTS_COLUMN_NAME)
    protected int maxMana;
    @Column(name = ARMOR_COLUMN_NAME)
    protected int armor;
    @Column(name = MAGIC_RESIST_COLUMN_NAME)
    protected int magicResistance;
    @Column(name = DAMAGE_COLUMN_NAME)
    protected int damage;
    @Column(name = ATTACK_SPEED_COLUMN_NAME)
    protected float attackSpeed;
    @Column(name = ATTACK_RANGE_COLUMN_NAME)
    protected float attackRange;
    @Column(name = MOVEMENT_SPEED_COLUMN_NAME)
    protected float movementSpeed;
    @Column(name = ACTIVITY_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    protected Activity activity;
    @Column(name = BATTLE_BEHAVIOR_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    protected BattleBehavior battleBehavior;
    @Column(name = UNIT_TYPE_COLUMN_NAME, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    protected UnitType unitType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = EquippedItem.TABLE_NAME, joinColumns = @JoinColumn(name = EquippedItem.UNIT_ID_COLUMN_NAME), inverseJoinColumns = @JoinColumn(name = EquippedItem.ITEM_ID_COLUMN_NAME))
    private Collection<Item> items = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "unit")
    private UnitSkills skills;

    public boolean isDamaged() {
        return maxHitPoints >= hitPoints;
    }

    public Valuable getTrainingCost() {
        return () -> 100;
    }

    public void setSkills(UnitSkills s) {
        s.setUnitId(id);
        s.setUnit(this);
        skills = s;
    }

    public <T extends UnitSkills> T getSkills() {
        return (T) skills;
    }

}

package org.gassangaming.model.item;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;

import static org.gassangaming.model.item.Item.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class Item {

    public static final String SEQUENCE_NAME = "s_items_id";
    public static final String TABLE_NAME = "items";
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String ITEM_TYPE_COLUMN_NAME = "item_type";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String RARITY_COLUMN_NAME = "rarity";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = USER_ID_COLUMN_NAME)
    private Long userId;

    @Column(name = ITEM_TYPE_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(name = NAME_COLUMN_NAME)
    private String name;

    @Column(name = RARITY_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;

    public Item() {
    }

    public Item(Long userId, ItemType itemType, String name, ItemRarity rarity) {
        this.userId = userId;
        this.itemType = itemType;
        this.name = name;
        this.rarity = rarity;
    }

    public Item(ItemType itemType, String name, ItemRarity rarity) {
        this(null, itemType, name, rarity);
    }

}

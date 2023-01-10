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

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    protected Long id;

    @Column(name = USER_ID_COLUMN_NAME)
    private long userId;

    @Column(name = ITEM_TYPE_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    protected ItemType itemType;
}

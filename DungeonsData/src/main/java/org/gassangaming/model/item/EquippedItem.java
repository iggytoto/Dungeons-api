package org.gassangaming.model.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

import static org.gassangaming.model.item.EquippedItem.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
@IdClass(EquippedItem.class)
@NoArgsConstructor
public class EquippedItem implements Serializable {

    public static final String TABLE_NAME = "items_units";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String ITEM_ID_COLUMN_NAME = Constants.ITEM_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column(name = ITEM_ID_COLUMN_NAME, insertable = false, updatable = false)
    protected long itemId;
    @Id
    @Column(name = UNIT_ID_COLUMN_NAME)
    protected long unitId;

//    @OneToOne
//    protected Item item;

    public EquippedItem(long itemId, long unitId) {
        this.itemId = itemId;
        this.unitId = unitId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class EquippedItemId implements Serializable {
        protected long itemId;
        protected long unitId;

    }
}

package org.gassangaming.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.gassangaming.model.UnitForSale.TABLE_NAME;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = TABLE_NAME)
@Getter
@Setter
public class UnitForSale extends Unit implements Valuable {

    public static final String TABLE_NAME = "units_for_sale";
    public static final String UNIT_ID_COLUMN_NAME = "unit_id";
    public static final String GOLD_AMOUNT_COLUMN_NAME = "gold_amount";

    @Column(name = UNIT_ID_COLUMN_NAME)
    private Long unitId;

    @Column(name = GOLD_AMOUNT_COLUMN_NAME)
    private long goldAmount;

    @Override
    public long getGoldCost() {
        return goldAmount;
    }
}

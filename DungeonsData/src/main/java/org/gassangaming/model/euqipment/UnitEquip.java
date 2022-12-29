package org.gassangaming.model.euqipment;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter
@Setter
public abstract class UnitEquip {

    public static final String SEQUENCE_NAME = "s_unit_equip_id";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    protected Long id;

    @Column(name = UNIT_ID_COLUMN_NAME)
    private long unitId;

}

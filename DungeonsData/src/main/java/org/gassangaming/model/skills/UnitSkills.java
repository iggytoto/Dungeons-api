package org.gassangaming.model.skills;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;
import org.gassangaming.model.unit.Unit;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter
@Setter
public abstract class UnitSkills {

    public static final String SEQUENCE_NAME = "s_unit_skills_id";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = UNIT_ID_COLUMN_NAME, insertable = false, updatable = false)
    private long unitId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UNIT_ID_COLUMN_NAME)
    private Unit unit;

}

package org.gassangaming.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

import static org.gassangaming.model.TrainingUnit.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
@IdClass(TrainingUnit.class)
public class TrainingUnit implements Serializable {

    public static final String TABLE_NAME = "training_units";
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column(name = USER_ID_COLUMN_NAME)
    protected long userId;

    @Id
    @Column(name = UNIT_ID_COLUMN_NAME)
    protected long unitId;

    protected TrainingUnit() {
    }

    public TrainingUnit(long userId, long unitId) {
        this.userId = userId;
        this.unitId = unitId;
    }

    public static TrainingUnit Of(long userId, long unitId) {
        return new TrainingUnit(userId, unitId);
    }
}

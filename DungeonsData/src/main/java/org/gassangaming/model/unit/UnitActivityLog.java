package org.gassangaming.model.unit;

import lombok.Getter;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;

import java.util.Date;

import static org.gassangaming.model.unit.UnitActivityLog.TABLE_NAME;


@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class UnitActivityLog {

    public static final String TABLE_NAME = "unit_activity_log";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String STARTED_COLUMN_NAME = "started_at";
    public static final String SEQUENCE_NAME = "s_user_activity_log_id";

    @Id
    @Column(name = UNIT_ID_COLUMN_NAME)
    private long unitId;

    @Column(name = STARTED_COLUMN_NAME)
    private Date startedAt;
}

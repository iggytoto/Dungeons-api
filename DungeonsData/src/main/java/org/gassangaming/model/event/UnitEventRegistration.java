package org.gassangaming.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gassangaming.model.Constants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = UnitEventRegistration.TABLE_NAME)
@IdClass(UnitEventRegistration.UnitEventRegistrationId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UnitEventRegistration {

    public static final String TABLE_NAME = "events_units";
    public static final String EVENT_ID_COLUMN_NAME = "event_id";
    public static final String UNIT_ID_COLUMN_NAME = Constants.UNIT_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;

    @Id
    @Column(name = EVENT_ID_COLUMN_NAME)
    protected long eventId;
    @Id
    @Column(name = UNIT_ID_COLUMN_NAME)
    protected long unitId;
    @Id
    @Column(name = USER_ID_COLUMN_NAME)
    protected long userId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UnitEventRegistrationId implements Serializable {
        protected long eventId;
        protected long unitId;
        protected long userId;
    }
}

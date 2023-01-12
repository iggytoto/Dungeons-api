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
@Table(name = UserEventRegistration.TABLE_NAME)
@IdClass(UserEventRegistration.UserEventRegistrationId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserEventRegistration implements Serializable{

    public static final String TABLE_NAME = "events_users";
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String EVENT_ID_COLUMN_NAME = "event_id";

    @Id
    @Column(name = USER_ID_COLUMN_NAME)
    protected long userId;
    @Id
    @Column(name = EVENT_ID_COLUMN_NAME)
    protected long eventId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserEventRegistrationId implements Serializable {
        protected long userId;
        protected long eventId;
    }
}

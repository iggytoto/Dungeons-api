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
@Table(name = UserEventInstance.TABLE_NAME)
@IdClass(UserEventInstance.UserEventInstanceId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserEventInstance implements Serializable{

    public static final String TABLE_NAME = "user_event_instances";
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String EVENT_ID_COLUMN_NAME = "event_id";
    public static final String EVENT_INSTANCE_ID_COLUMN_NAME = "event_instance_id";

    @Id
    @Column(name = USER_ID_COLUMN_NAME)
    private long userId;

    @Id
    @Column(name = EVENT_ID_COLUMN_NAME)
    private long eventId;

    @Id
    @Column(name = EVENT_INSTANCE_ID_COLUMN_NAME)
    private long eventInstanceId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserEventInstanceId implements Serializable {
        protected long userId;
        protected long eventId;
        protected long eventInstanceId;
    }
}

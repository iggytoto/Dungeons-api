package org.gassangaming.model.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static org.gassangaming.model.event.EventInstance.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class EventInstance {

    public static final String SEQUENCE_NAME = "s_event_instance_id";

    public static final String TABLE_NAME = "event_instances";
    public static final String EVENT_ID_COLUMN_NAME = "event_id";
    public static final String HOST_COLUMN_NAME = "host";
    public static final String PORT_COLUMN_NAME = "port";
    public static final String EVENT_INSTANCE_STATUS_COLUMN_NAME = "status";
    public static final String EVENT_TYPE_COLUMN_NAME = "event_type";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    private long id;

    @Column(name = EVENT_ID_COLUMN_NAME)
    private long eventId;

    @Column(name = HOST_COLUMN_NAME)
    private String host;
    @Column(name = PORT_COLUMN_NAME)
    private String port;
    @Column(name = EVENT_INSTANCE_STATUS_COLUMN_NAME)
    @Enumerated(STRING)
    private EventInstanceStatus status;
    @Column(name = EVENT_TYPE_COLUMN_NAME)
    @Enumerated(STRING)
    private EventType eventType;

    public EventInstance() {
    }

    public EventInstance(long eventId, EventType type, EventInstanceStatus status) {
        this.eventId = eventId;
        this.status = status;
        this.eventType = type;
    }

}

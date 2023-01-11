package org.gassangaming.model.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.gassangaming.model.event.Event.TABLE_NAME;

@Entity
@Getter
@Setter
@Table(name = TABLE_NAME)
public class Event {

    public static final String SEQUENCE_NAME = "s_events_id";
    public static final String EVENT_TYPE_COLUMN_NAME = "event_type";
    public static final String STATUS_COLUMN_NAME = "status";

    public static final String TABLE_NAME = "events";

    @Id
    private long id;

    @Column(name = EVENT_TYPE_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = STATUS_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    protected Event() {
    }

    public static Event of(EventType type, EventStatus status) {
        final var e = new Event();
        e.setEventType(type);
        e.setStatus(status);
        return e;
    }

}

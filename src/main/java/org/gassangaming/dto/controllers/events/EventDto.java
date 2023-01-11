package org.gassangaming.dto.controllers.events;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.model.event.Event;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class EventDto extends DtoBase {


    public static EventDto of(Event e) {
        return null;
    }
}

package org.gassangaming.dto.controllers.events.eventinstanceresult;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.model.event.EventType;
import org.gassangaming.service.event.EventInstanceResult;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class EventInstanceResultDto extends DtoBase {
    long eventInstanceId;
    EventType eventType;

    public abstract EventInstanceResult toDomain();
}

package org.gassangaming.dto.controllers.events;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class EventsStatusResponseDto extends DtoBase {
    Collection<EventDto> events;
}

package org.gassangaming.dto.controllers.events.eventinstanceresult;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.service.event.PhoenixRaidEventInstanceResult;

@Data
@EqualsAndHashCode(callSuper = true)
public class PhoenixRaidEventInstanceResultDto extends EventInstanceResultDto {
    
    @Override
    public PhoenixRaidEventInstanceResult toDomain() {
        return null;
    }
}

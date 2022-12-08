package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class TrainUnitRequest extends DtoBase {
    long unitId;
}

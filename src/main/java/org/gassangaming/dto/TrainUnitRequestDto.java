package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TrainUnitRequestDto extends DtoBase {
    long unitId;
}

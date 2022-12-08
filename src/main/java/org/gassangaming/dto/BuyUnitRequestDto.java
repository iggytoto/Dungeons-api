package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BuyUnitRequestDto extends DtoBase {
    long unitId;
}

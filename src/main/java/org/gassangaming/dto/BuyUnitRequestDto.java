package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.model.unit.UnitType;

@EqualsAndHashCode(callSuper = true)
@Data
public class BuyUnitRequestDto extends DtoBase {
    UnitType type;
}

package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gassangaming.dto.DtoBase;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BuyUnitRequestDto extends DtoBase {
    long unitId;
}

package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeUnitNameRequestDto extends DtoBase{
    long unitId;
    String newName;
}

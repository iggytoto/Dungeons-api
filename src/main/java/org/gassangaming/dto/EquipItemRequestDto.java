package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipItemRequestDto extends DtoBase {
    long itemId;
    long unitId;
}

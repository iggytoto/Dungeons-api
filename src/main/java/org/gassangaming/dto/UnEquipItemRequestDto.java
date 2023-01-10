package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnEquipItemRequestDto extends DtoBase {
    long itemId;
}

package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class UnitListResponseDto<TUnit extends UnitDto> extends DtoBase {
    Collection<TUnit> units;

    public static UnitListResponseDto<?> Of(Collection<? extends UnitDto> units){
        return new UnitListResponseDto<>(units);
    }
}

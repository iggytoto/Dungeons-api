package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.unit.UnitDto;

import java.util.Collection;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class ListResponseDto<TUnit extends DtoBase> extends DtoBase {
    Collection<TUnit> units;

    public static ListResponseDto<?> Of(Collection<? extends UnitDto> units){
        return new ListResponseDto<>(units);
    }
}

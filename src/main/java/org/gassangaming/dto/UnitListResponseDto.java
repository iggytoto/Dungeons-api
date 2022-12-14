package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.model.Unit;

import java.util.Collection;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class UnitListResponseDto<TUnit extends Unit> extends DtoBase {
    Collection<TUnit> units;

    public static UnitListResponseDto<?> Of(Collection<? extends Unit> units){
        return new UnitListResponseDto<>(units);
    }
}

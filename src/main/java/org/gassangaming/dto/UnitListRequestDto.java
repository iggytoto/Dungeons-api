package org.gassangaming.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gassangaming.model.Unit;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UnitListRequestDto<TUnit extends Unit> extends DtoBase {
    Collection<TUnit> units;

    @JsonCreator
    public UnitListRequestDto(Collection<TUnit> units) {
        this.units = units;
    }

    public static UnitListRequestDto<?> Of(Collection<? extends Unit> units) {
        return new UnitListRequestDto<>(units);
    }
}

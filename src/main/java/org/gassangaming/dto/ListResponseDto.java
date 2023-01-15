package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class ListResponseDto<T extends DtoBase> extends DtoBase {
    Collection<T> items;
    public static ListResponseDto<?> of(Collection<? extends DtoBase> units){
        return new ListResponseDto<>(units);
    }
}

package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectResponseDto<TObject extends Serializable> extends DtoBase {
    TObject obj;
}

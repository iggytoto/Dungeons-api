package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.model.Unit;

import java.util.Collection;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class GetAvailableUnitsResponse extends DtoBase {

    Collection<Unit> units;
}

package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.model.Match;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchMakingGetStatusResponseDto extends DtoBase {
    Match status;
}

package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.model.Match;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class MatchMakingServerApplyResponseDto extends DtoBase {
    Match match;
}

package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchMakingServerApplyRequestDto extends DtoBase {
    String ip;
    String port;
}

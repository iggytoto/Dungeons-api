package org.gassangaming.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class LoginResponseDto extends DtoBase {
    String token;
}

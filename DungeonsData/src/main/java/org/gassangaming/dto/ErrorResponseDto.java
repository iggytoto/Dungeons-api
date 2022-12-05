package org.gassangaming.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
public class ErrorResponseDto extends DtoBase {

    public static ErrorResponseDto Of(String s) {
        final var result = new ErrorResponseDto();
        result.setCode(1);
        result.setMessage(s);
        return result;
    }
}

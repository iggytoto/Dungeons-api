package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LoginRequestDto extends DtoBase {

    String login;
    String password;
}

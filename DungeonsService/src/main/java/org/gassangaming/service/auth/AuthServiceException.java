package org.gassangaming.service.auth;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthServiceException extends Exception {
    public AuthServiceException(String message) {
        super(message);
    }
}

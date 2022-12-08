package org.gassangaming.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }
}

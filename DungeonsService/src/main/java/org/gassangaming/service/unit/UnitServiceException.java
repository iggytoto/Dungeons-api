package org.gassangaming.service.unit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnitServiceException extends Exception {

    public UnitServiceException(String message) {
        super(message);
    }
}

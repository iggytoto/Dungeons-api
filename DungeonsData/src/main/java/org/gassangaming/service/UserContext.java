package org.gassangaming.service;

import lombok.Builder;
import lombok.Data;
import org.gassangaming.model.Token;

@Data
@Builder
public class UserContext {

    public final static String CONTEXT_ATTRIBUTE_NAME = "context";
    private Token token;
}

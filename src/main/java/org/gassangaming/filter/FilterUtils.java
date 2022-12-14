package org.gassangaming.filter;

import org.gassangaming.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

final class FilterUtils {
    static void writeErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.getWriter().write(ErrorResponseDto.Of(message).toJson());
        response.setStatus(status.value());
        response.getWriter().flush();
    }
}

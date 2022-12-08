package org.gassangaming.filter;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.gassangaming.dto.ErrorResponseDto;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.auth.AuthService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final UserContext context;
            try {
                context = tokenService.validateToken(authHeader.substring(7));
                if (context != null) {
                    request.setAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME, context);
                    filterChain.doFilter(request, response);
                    return;
                }
            } catch (ServiceException se) {
                writeErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, se.getMessage());
            } catch (InvalidDefinitionException jde) {
                writeErrorResponse(response, HttpStatus.BAD_REQUEST, jde.getMessage());
                return;
            }
        }
        writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains("/auth");
    }

    private void writeErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.getWriter().write(ErrorResponseDto.Of(message).toJson());
        response.getWriter().flush();
    }
}

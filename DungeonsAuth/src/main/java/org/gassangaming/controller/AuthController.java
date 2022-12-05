package org.gassangaming.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.io.BaseEncoding;
import org.gassangaming.dto.*;
import org.gassangaming.service.auth.AuthService;
import org.gassangaming.service.auth.AuthServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public DtoBase login(@RequestBody LoginRequestDto request) throws JsonProcessingException {
        try {
            final var token = authService.login(request.getLogin(), request.getPassword());
            return LoginResponseDto.builder().token(BaseEncoding.base64().encode(token.toJson().getBytes())).build();
        } catch (AuthServiceException ase) {
            return ErrorResponseDto.Of(ase.getMessage());
        }
    }

    @PostMapping("/auth/register")
    public DtoBase register(@RequestBody RegisterRequestDto request) {
        try {
            return RegisterResponseDto.builder().userId(authService.register(request.getLogin(), request.getPassword())).build();
        } catch (AuthServiceException ase) {
            return ErrorResponseDto.Of(ase.getMessage());
        }
    }

}

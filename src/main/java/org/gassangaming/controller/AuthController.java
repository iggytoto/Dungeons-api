package org.gassangaming.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.io.BaseEncoding;
import org.gassangaming.dto.LoginRequestDto;
import org.gassangaming.dto.LoginResponseDto;
import org.gassangaming.dto.RegisterRequestDto;
import org.gassangaming.dto.RegisterResponseDto;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.dto.ErrorResponseDto;
import org.gassangaming.service.auth.AuthService;
import org.gassangaming.service.exception.ServiceException;
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
        } catch (ServiceException ase) {
            return ErrorResponseDto.Of(ase.getMessage());
        }
    }

    @PostMapping("/auth/register")
    public DtoBase register(@RequestBody RegisterRequestDto request) {
        try {
            return RegisterResponseDto.builder().userId(authService.register(request.getLogin(), request.getPassword())).build();
        } catch (ServiceException ase) {
            return ErrorResponseDto.Of(ase.getMessage());
        }
    }

}

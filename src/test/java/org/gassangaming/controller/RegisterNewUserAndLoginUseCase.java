package org.gassangaming.controller;

import org.gassangaming.dto.LoginRequestDto;
import org.gassangaming.dto.LoginResponseDto;
import org.gassangaming.dto.RegisterRequestDto;
import org.gassangaming.dto.RegisterResponseDto;
import org.gassangaming.repository.TokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterNewUserAndLoginUseCase {


    private static final String LOGIN = "testLogin";
    private static final String PASSWORD = "123456789_QWERasd!@#$%^&";


    @Autowired
    AuthController authController;

    @Autowired
    TokenRepository tokenRepository;

    @Test
    public void testCase() {
        final var registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setLogin(LOGIN);
        registerRequestDto.setPassword(PASSWORD);
        final var registerResponseDtoBase = authController.register(registerRequestDto);
        Assert.notNull(registerResponseDtoBase, "Should not be null");
        Assert.isTrue(registerResponseDtoBase instanceof RegisterResponseDto, "Should be of valid response type");
        final var registerResponseDto = (RegisterResponseDto) registerResponseDtoBase;
        final var newUserId = registerResponseDto.getUserId();
        Assert.isTrue(newUserId > 0, "Should be valid new id");

        final var loginRequestDto = new LoginRequestDto();
        loginRequestDto.setLogin(LOGIN);
        loginRequestDto.setPassword(PASSWORD);
        final var loginResponseDtoBase = authController.login(loginRequestDto);
        Assert.notNull(loginResponseDtoBase, "Should not be null");
        Assert.isTrue(loginResponseDtoBase instanceof LoginResponseDto, "Should be of valid response type");
        final var loginResponseDto = (LoginResponseDto) loginResponseDtoBase;
        Assert.notNull(loginResponseDto.getToken(), "token should not be empty");
        Assert.isTrue(loginResponseDto.getToken().length() > 0, "token should not be empty");

    }

}
package org.gassangaming.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import org.apache.commons.lang3.time.DateUtils;
import org.gassangaming.model.Token;
import org.gassangaming.model.User;
import org.gassangaming.repository.TokenRepository;
import org.gassangaming.repository.UserRepository;
import org.gassangaming.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Token login(String login, String password) throws AuthServiceException {
        final var u = userRepository.findUserByLogin(login);
        if (u != null) {
            final var passHash = hashString(password);
            if (u.getPasswordSha().equals(passHash)) {
                return tokenRepository.save(getToken(u.getId()));
            } else {
                throw new AuthServiceException("Incorrect password");
            }
        } else {
            throw new AuthServiceException("Unknown user");
        }
    }

    private Token getToken(long userId) {
        final var token = tokenRepository.findByUserId(userId);
        final var now = new Date();
        if (token == null) {
            return Token.Of(hashString(now.toString()), userId, DateUtils.addHours(now, 1));
        } else {
            if (token.isPermanent()) {
                return token;
            }
            return Token.Of(token.getId(), hashString(now.toString()), userId, DateUtils.addHours(now, 1));
        }
    }

    @Override
    public long register(String login, String password) throws AuthServiceException {
        final var u = userRepository.findUserByLogin(login);
        if (u != null) {
            throw new AuthServiceException("Login already in use");
        } else {
            final var passHash = hashString(password);
            final var registeredUser = userRepository.save(User.Of(login, passHash));
            return registeredUser.getId();
        }
    }

    @Override
    public UserContext validateToken(String t) throws AuthServiceException {
        try {
            final var now = new Date();
            final var token = Token.Of(new String(BaseEncoding.base64().decode(t)));
            final var actualToken = tokenRepository.findByUserId(token.getUserId());
            if (actualToken.getValue().equals(token.getValue()) && (actualToken.isPermanent() || actualToken.getValidTo().after(now))) {
                actualToken.setValidTo(DateUtils.addHours(now, 1));
                final var updatedToken = tokenRepository.save(actualToken);
                return UserContext.builder().token(updatedToken).build();
            } else {
                throw new AuthServiceException("Invalid token");
            }
        } catch (JsonProcessingException e) {
            throw new AuthServiceException(e.getMessage());
        }
    }

    private String hashString(String s) {
        return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
    }
}

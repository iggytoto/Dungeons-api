package org.gassangaming.service.auth;

import org.gassangaming.model.Token;
import org.gassangaming.service.UserContext;
import org.springframework.stereotype.Service;

/**
 * Service that provides the functionality of registering new users and login to the system.
 */
@Service
public interface AuthService {

    /**
     * Logins and provides the token update
     *
     * @param login    login
     * @param password password
     * @return updated token
     * @throws AuthServiceException in case login failed
     */
    Token login(String login, String password) throws AuthServiceException;

    /**
     * Registers the use
     *
     * @param login    login
     * @param password password
     * @return userId in case everythis is ok
     * @throws AuthServiceException in case register failed
     */
    long register(String login, String password) throws AuthServiceException;

    /**
     * Validates given token, if its valid prolongs its validation to +1 hour.
     *
     * @param token given token
     * @throws AuthServiceException in case validation failed
     */
    UserContext validateToken(String token) throws AuthServiceException;
}

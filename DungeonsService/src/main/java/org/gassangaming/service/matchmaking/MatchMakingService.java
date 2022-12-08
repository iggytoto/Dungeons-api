package org.gassangaming.service.matchmaking;

import org.gassangaming.model.Match;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Matchmaking service.
 */
@Service
public interface MatchMakingService {

    /**
     * Register request for mm
     *
     * @param rosterTeam ids of units player roster
     * @param type       ladder type
     * @param context    User context
     * @throws org.gassangaming.service.exception.ServiceException in case registration for this user already exists or match already setup
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    void register(Collection<Long> rosterTeam, MatchMakingType type, UserContext context) throws ServiceException;

    /**
     * Cancel mm request
     *
     * @param context user context
     * @throws org.gassangaming.service.exception.ServiceException in case there is no registration for this user or match already in progress
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    void cancelRegistration(UserContext context) throws ServiceException;

    /**
     * Gets the current state for given user
     *
     * @param context User context
     * @return state of given user mm status
     */
    Match getStatus(UserContext context);
}

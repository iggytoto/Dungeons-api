package org.gassangaming.service.matchmaking;

import org.gassangaming.model.Match;
import org.gassangaming.model.MatchType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Matchmaking service.
 */
@Service
public interface MatchMakingService {

    /**
     * Register request for mm. This method is not applicable for servers.
     *
     * @param rosterTeam ids of units player roster
     * @param type       ladder type
     * @param context    User context
     * @throws org.gassangaming.service.exception.ServiceException in case registration for this user already exists or match already setup
     */
    @Transactional
    Match register(Collection<Long> rosterTeam, MatchType type, UserContext context) throws ServiceException;

    /**
     * Cancel mm request for user. This method is not applicable for servers.
     *
     * @param context user context
     * @throws org.gassangaming.service.exception.ServiceException in case there is no registration for this user or match already in progress
     */
    @Transactional
    void cancelRegistration(UserContext context) throws ServiceException;

    /**
     * Gets the current state for given client user. This method is not applicable for servers.
     *
     * @param context User context
     * @return state of given user mm status
     */
    Match getStatus(UserContext context);

    /**
     * Server application to make a match. Server suggests its services to host the match, finds latest match
     * that awaits for the server -> in state {@link org.gassangaming.model.MatchStatus#PlayersFound} and applies for it.
     *
     * @return Match info in case server applied on the match, null other cases. Should have {@link org.gassangaming.model.MatchStatus#ServerFound}
     * in result
     */
    @Transactional
    Match applyServer(String ip, String port);
}

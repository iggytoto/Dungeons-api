package org.gassangaming.service.matchmaking;

import org.gassangaming.model.Match;
import org.gassangaming.model.MatchStatus;
import org.gassangaming.repository.MatchRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MatchMakingServiceImpl implements MatchMakingService {

    @Autowired
    MatchRepository matchRepository;

    @Override
    public void register(Collection<Long> rosterTeam, MatchMakingType type, UserContext context) throws ServiceException {
        final var userId = context.getToken().getUserId();
        if (matchRepository.matchExistsForUser(userId)) {
            throw new ServiceException("Match already exists");
        }
        final var match = matchRepository.findFirstFreeToJoinMatch();
        if (match == null) {
            matchRepository.save(Match.Of(userId, MatchStatus.Searching));
        } else {
            match.setUserTwoId(userId);
            match.setStatus(MatchStatus.PlayersFound);
            matchRepository.save(match);
        }
    }

    @Override
    public void cancelRegistration(UserContext context) throws ServiceException {
        final var userId = context.getToken().getUserId();
        if (!matchRepository.matchExistsForUser(userId)) {
            throw new ServiceException("Match already exists");
        }
        final var match = matchRepository.findCancellableForUser(userId);
        if (match == null) {
            throw new ServiceException("Failed to cancel the match, probably already started");
        }
        matchRepository.delete(match);
    }

    @Override
    public Match getStatus(UserContext context) {
        return matchRepository.findForUser(context.getToken().getUserId());
    }
}

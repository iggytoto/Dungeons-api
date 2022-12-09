package org.gassangaming.service.matchmaking;

import org.gassangaming.model.Match;
import org.gassangaming.model.MatchStatus;
import org.gassangaming.repository.MatchMakingRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class MatchMakingServiceImpl implements MatchMakingService {

    @Autowired
    MatchMakingRepository matchRepository;

    @Override
    public void register(Collection<Long> rosterTeam, MatchMakingType type, UserContext context) throws ServiceException {
        final var userId = context.getToken().getUserId();
        if (matchRepository.hasRegistered(userId)) {
            throw new ServiceException("Match already exists");
        }
        final var match = matchRepository.findFirstFree();
        if (match == null) {
            matchRepository.save(Match.Of(userId, MatchStatus.Searching, new Date()));
        } else {
            match.setUserTwoId(userId);
            match.setStatus(MatchStatus.PlayersFound);
            matchRepository.save(match);
        }
    }

    @Override
    public void cancelRegistration(UserContext context) throws ServiceException {
        final var userId = context.getToken().getUserId();
        if (!matchRepository.hasRegistered(userId)) {
            throw new ServiceException("Match already exists");
        }
        final var match = matchRepository.findCancellableForUser(userId);
        if (match == null) {
            throw new ServiceException("Failed to cancel the match, probably already started");
        }
        matchRepository.delete(match);
    }

    @Override
    public Match getStatus(UserContext context) throws ServiceException {
        final var match = matchRepository.findForUser(context.getToken().getUserId());
        if (match == null) {
            throw new ServiceException("Match not found");
        }
        return match;
    }

    @Override
    public Match applyServer(String ip, String port) {
        final var match = matchRepository.findFirstOldestAwaitingServer();
        match.setServerIpAddress(ip);
        match.setServerPort(port);
        return matchRepository.save(match);
    }
}

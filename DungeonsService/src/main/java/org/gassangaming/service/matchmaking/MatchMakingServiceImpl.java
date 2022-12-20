package org.gassangaming.service.matchmaking;

import org.gassangaming.model.*;
import org.gassangaming.repository.MatchMakingRepository;
import org.gassangaming.repository.TrainingUnitRepository;
import org.gassangaming.repository.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class MatchMakingServiceImpl implements MatchMakingService {

    @Autowired
    MatchMakingRepository matchRepository;

    @Autowired
    TrainingUnitRepository trainingUnitRepository;

    @Autowired
    UnitRepository unitRepository;

    @Override
    public Match register(Collection<Long> rosterTeam, MatchType type, UserContext context) throws ServiceException {
        final var userId = context.getToken().getUserId();
        if (matchRepository.hasRegistered(userId)) {
            throw new ServiceException("Match already exists");
        }
        trainingUnitRepository.saveAll(rosterTeam.stream().map(unitId -> TrainingUnit.Of(userId, unitId)).collect(Collectors.toList()));
        rosterTeam.forEach(unitId -> unitRepository.updateActivityByUnitId(unitId, Activity.Training));

        final var match = matchRepository.findFirstFree();
        if (match == null) {
            return matchRepository.save(Match.Of(userId, MatchStatus.Searching, new Date()));
        } else {
            match.setUserTwoId(userId);
            match.setStatus(MatchStatus.PlayersFound);
            return matchRepository.save(match);
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
        trainingUnitRepository.deleteAllForUser(userId);
    }

    @Override
    public Match getStatus(UserContext context) {
        return matchRepository.findForUser(context.getToken().getUserId());
    }

    @Override
    public Match applyServer(String ip, String port) {
        final var match = matchRepository.findFirstOldestAwaitingServer();
        if (match == null) {
            return null;
        }
        match.setServerIpAddress(ip);
        match.setServerPort(port);
        match.setStatus(MatchStatus.ServerFound);
        return matchRepository.save(match);
    }
}

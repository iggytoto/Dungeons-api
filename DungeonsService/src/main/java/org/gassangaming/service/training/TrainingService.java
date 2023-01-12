package org.gassangaming.service.training;

import org.gassangaming.model.MatchResult;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.service.barrack.UnitState;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Training yard service. This is server only service that provides functional to start the training session between
 * two players by the server.
 */
@Service
public interface TrainingService {

    /**
     * Gets the roster for given user id.
     *
     * @param userId id of user for the roster
     * @return set of units that user is registered for the training yard match
     * @throws ServiceException i do not remember why it is here, get operations should not return exceptions
     */
    Collection<UnitState> getTrainingRosterForUser(long userId) throws ServiceException;

    /**
     * Server saves match result after match ended with this call.
     * @param result match results
     * @param unitsState unit states of the training session
     * @throws ServiceException something is wrong, there should not be exceptions here.
     */
    @Transactional
    void saveTrainingResult(MatchResult result, Collection<Unit> unitsState) throws ServiceException;
}

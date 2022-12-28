package org.gassangaming.service.training;

import org.gassangaming.model.MatchResult;
import org.gassangaming.model.Unit;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public interface TrainingService {

    Collection<Unit> getTrainingRosterForUser(long userId) throws ServiceException;

    @Transactional
    void saveTrainingResult(MatchResult result, Collection<Unit> unitsState) throws ServiceException;
}

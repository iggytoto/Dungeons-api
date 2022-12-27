package org.gassangaming.service.training;

import org.gassangaming.model.Activity;
import org.gassangaming.model.MatchResult;
import org.gassangaming.model.TrainingUnit;
import org.gassangaming.model.Unit;
import org.gassangaming.repository.MatchResultRepository;
import org.gassangaming.repository.TrainingUnitRepository;
import org.gassangaming.repository.UnitRepository;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    MatchResultRepository matchResultRepository;
    @Autowired
    TrainingUnitRepository trainingUnitRepository;
    @Autowired
    UnitRepository unitRepository;


    @Override
    public Collection<Unit> getTrainingRosterForUser(long userId) {
        return unitRepository.findAllById(trainingUnitRepository.getUnitsForUser(userId).stream().map(TrainingUnit::getUnitId).collect(Collectors.toList()));
    }

    @Override
    public void saveRosters(Collection<Unit> unitsToUpdate) throws ServiceException {
        unitRepository.saveAll(unitsToUpdate);
    }

    @Override
    public void saveTrainingResult(MatchResult result) {
        matchResultRepository.save(result);
        trainingUnitRepository.deleteAllForUser(result.getUserOneId());
        trainingUnitRepository.getUnitsForUser(result.getUserOneId()).forEach(u -> unitRepository.updateActivityByUnitId(u.getUnitId(), Activity.Idle));
        trainingUnitRepository.deleteAllForUser(result.getUserTwoId());
        trainingUnitRepository.getUnitsForUser(result.getUserOneId()).forEach(u -> unitRepository.updateActivityByUnitId(u.getUnitId(), Activity.Idle));
    }
}

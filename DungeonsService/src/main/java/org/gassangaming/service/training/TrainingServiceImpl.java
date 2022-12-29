package org.gassangaming.service.training;

import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.MatchResult;
import org.gassangaming.model.unit.TrainingUnit;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.MatchResultRepository;
import org.gassangaming.repository.TrainingUnitRepository;
import org.gassangaming.repository.UnitRepository;
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
    public void saveTrainingResult(MatchResult result, Collection<Unit> unitsState) {
        matchResultRepository.save(result);
        trainingUnitRepository.deleteAllForUser(result.getUserOneId());
        trainingUnitRepository.deleteAllForUser(result.getUserTwoId());
        unitRepository.saveAll(unitsState.stream().map(this::mergeUnit).collect(Collectors.toList()));
    }

    /**
     * Merges only params that can be updated after training match.
     * During training results we can change only:
     * - current hit points is modified by the server
     * - training experience is modified by the server
     * - activity should be set to Idle
     */
    private Unit mergeUnit(Unit unit) {
        final var u = unitRepository.findById(unit.getId()).orElseThrow();
        u.setTrainingExperience(unit.getTrainingExperience());
        u.setHitPoints(unit.getHitPoints());
        u.setActivity(Activity.Idle);
        return u;
    }

}

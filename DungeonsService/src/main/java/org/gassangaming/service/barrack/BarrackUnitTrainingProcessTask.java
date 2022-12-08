package org.gassangaming.service.barrack;

import org.gassangaming.model.Activity;
import org.gassangaming.model.UnitActivityLog;
import org.gassangaming.repository.UnitActivityLogRepository;
import org.gassangaming.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BarrackUnitTrainingProcessTask {

    private static final int TRAINING_TIME_MILLS = 1000 * 60 * 60 * 8;

    @Autowired
    UnitActivityLogRepository ualRepository;

    @Autowired
    UnitRepository unitRepository;

    @Transactional
    @Scheduled(fixedRate = 1000 * 60)
    public void scheduleUnitTrainingProcess() {
        ualRepository.findByActivity(Activity.Training.toString()).forEach(this::processTrainingUnit);
    }

    private void processTrainingUnit(UnitActivityLog activityLog) {
        final Date now = new Date();
        if (Math.abs(activityLog.getStartedAt().getTime() - now.getTime()) > TRAINING_TIME_MILLS) {
            unitRepository.updateActivityByUnitId(activityLog.getUnitId(), Activity.Idle);
        }
    }
}

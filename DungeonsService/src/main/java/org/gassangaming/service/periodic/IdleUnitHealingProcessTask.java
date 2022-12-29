package org.gassangaming.service.periodic;

import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
public class IdleUnitHealingProcessTask {

    @Autowired
    UnitRepository unitRepository;


    @Transactional
    @Scheduled(fixedRate = 1000 * 60 * 3)
    public void scheduleIdleUnitHealing() {
        unitRepository.saveAll(unitRepository.findByActivity(Activity.Idle).stream().map(this::healUnit).collect(Collectors.toList()));
    }

    private Unit healUnit(Unit unit) {
        if (unit.isDamaged()) {
            unit.setHitPoints(unit.getHitPoints() + 1);
        }
        return unit;
    }
}

package org.gassangaming.service.barrack;

import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public interface BarrackService {
    Collection<UnitState> getBarrackUnits(UserContext context) throws ServiceException;

    @Transactional
    void TrainUnit(long unitId, UserContext context) throws ServiceException;

    @Transactional
    void ChangeUnitName(long unitId, String newName, UserContext context) throws ServiceException;

    @Transactional
    void ChangeUnitBattleBehavior(long unitId, BattleBehavior newBattleBehavior, UserContext context) throws ServiceException;
}

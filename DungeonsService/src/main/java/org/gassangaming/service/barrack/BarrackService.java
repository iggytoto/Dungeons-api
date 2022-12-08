package org.gassangaming.service.barrack;

import org.gassangaming.model.Unit;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public interface BarrackService {
    Collection<Unit> getBarrackUnits(UserContext context) throws ServiceException;

    @Transactional
    void TrainUnit(long unitId, UserContext context) throws ServiceException;
}

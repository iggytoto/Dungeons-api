package org.gassangaming.service.tavern;

import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Game logic service that provides functional of tavern or unit market.
 */
@Service
public interface TavernService {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Unit buyUnit(UnitType type, UserContext context) throws ServiceException;
}

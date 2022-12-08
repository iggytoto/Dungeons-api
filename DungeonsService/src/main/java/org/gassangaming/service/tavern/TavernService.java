package org.gassangaming.service.tavern;

import org.gassangaming.model.UnitForSale;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Game logic service that provides functional of tavern or unit market.
 */
@Service
public interface TavernService {

    Collection<UnitForSale> getUnitsForSale(UserContext context) throws ServiceException;


    @Transactional(isolation = Isolation.SERIALIZABLE)
    void buyUnit(long unitId, UserContext context) throws ServiceException;
}

package org.gassangaming.service.tavern;

import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Game logic service that provides functional of tavern or unit market.
 */
@Service
public interface TavernService {

    /**
     * Buys unit for the player. Creates new entity sets ownerid with player id value.
     *
     * @param type    unit type to buy
     * @param userId player id
     * @return net unit state
     * @throws ServiceException if player does not have money for purchase.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    Unit buyUnit(UnitType type, long userId) throws ServiceException;
}

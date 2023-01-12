package org.gassangaming.service.tavern;

import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.barrack.UnitState;
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
     * @param context player context
     * @return net unit state
     * @throws ServiceException if player does not have money for purchase.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    UnitState buyUnit(UnitType type, UserContext context) throws ServiceException;
}

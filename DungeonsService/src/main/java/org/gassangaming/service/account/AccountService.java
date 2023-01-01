package org.gassangaming.service.account;

import org.gassangaming.model.Valuable;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;

/**
 * Service that controls player account and its resources.
 */
@Service
public interface AccountService {

    /**
     * Buys valuable for a player.
     *
     * @param v           valuable to buy.
     * @param userContext player context;
     * @throws ServiceException in case there is not enough resources for purchase.
     */
    void buyItem(Valuable v, UserContext userContext) throws ServiceException;

}

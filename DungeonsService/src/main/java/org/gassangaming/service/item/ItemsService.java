package org.gassangaming.service.item;

import org.gassangaming.model.item.Item;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Main service represents all activities with the items in the game
 */
@Service
public interface ItemsService {
    /**
     * Get all unequipped items for player
     *
     * @param userId player id
     * @return
     */
    Collection<Item> getAllUnequippedItemsForPlayer(long userId);

    /**
     * Equips given item to given unit
     */
    void equipItem(long itemId, long unitId, UserContext context) throws ServiceException;

    /**
     * unequips item
     */
    void unEquipItem(long itemId, UserContext context) throws ServiceException;
}

package org.gassangaming.service.item;

import org.gassangaming.model.item.EquippedItem;
import org.gassangaming.model.item.Item;
import org.gassangaming.repository.item.ItemRepository;
import org.gassangaming.repository.item.UnitItemsRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemRepository repository;
    @Autowired
    UnitItemsRepository unitItemsRepository;
    @Autowired
    UnitRepository unitRepository;

    @Override
    public Collection<Item> getAllUnequippedItemsForPlayer(long userId) {
        return repository.getAllUnequippedItemsForPlayer(userId);
    }

    @Override
    public void equipItem(long itemId, long unitId, UserContext context) throws ServiceException {
        checkItemRights(itemId, context.getToken().getUserId());
        final var unit = unitRepository.getReferenceById(unitId);
        if (unit.getOwnerId() != context.getToken().getUserId()) {
            throw new ServiceException("You cannot control not your units");
        }
        unitItemsRepository.save(new EquippedItem(itemId, unitId));
    }

    @Override
    public void unEquipItem(long itemId, UserContext context) throws ServiceException {
        checkItemRights(itemId, context.getToken().getUserId());
        unitItemsRepository.deleteByItemId(itemId);
    }

    private void checkItemRights(long itemId, long userId) throws ServiceException {
        if (repository.getReferenceById(itemId).getUserId() != userId) {
            throw new ServiceException("You cannot control not your items");
        }
    }
}

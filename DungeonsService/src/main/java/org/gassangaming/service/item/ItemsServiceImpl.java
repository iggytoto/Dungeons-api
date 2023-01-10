package org.gassangaming.service.item;

import org.gassangaming.model.item.Item;
import org.gassangaming.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemRepository repository;

    @Override
    public Collection<Item> getAllUnequippedItemsForPlayer(long userId) {
        return repository.findByOwnerId(userId);
    }
}

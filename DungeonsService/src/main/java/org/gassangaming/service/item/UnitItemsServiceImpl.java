package org.gassangaming.service.item;

import org.gassangaming.model.item.Item;
import org.gassangaming.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UnitItemsServiceImpl implements UnitItemsService {

    @Autowired
    ItemRepository itemRepository;


    @Override
    public Collection<Item> getForUnit(long id) {
        return itemRepository.getAllForUnit(id);
    }
}

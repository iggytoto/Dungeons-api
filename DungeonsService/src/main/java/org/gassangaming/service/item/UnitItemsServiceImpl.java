package org.gassangaming.service.item;

import org.gassangaming.model.item.Item;
import org.gassangaming.repository.item.UnitItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class UnitItemsServiceImpl implements UnitItemsService {

    @Autowired
    UnitItemsRepository unitItemsRepository;

    @Override
    public Collection<Item> getForUnit(long id) {
        return unitItemsRepository.findByUnitId(id);
    }
}

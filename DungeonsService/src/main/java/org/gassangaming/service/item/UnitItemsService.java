package org.gassangaming.service.item;

import org.gassangaming.model.item.Item;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UnitItemsService {
    Collection<Item> getForUnit(long id);
}

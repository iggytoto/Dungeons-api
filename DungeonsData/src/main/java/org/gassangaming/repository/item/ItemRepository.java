package org.gassangaming.repository.item;

import org.gassangaming.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface ItemRepository extends JpaRepository<Item, Long> {

    String GET_ALL_UNEQUIPPED_FOR_USER = "SELECT * FROM items i LEFT JOIN items_units iu ON i.id=iu.item_id WHERE iu.item_id is null and i.unitId=:" + PARAM_1;

    @Query(value = GET_ALL_UNEQUIPPED_FOR_USER)
    Collection<Item> getAllUnequippedItemsForPlayer(long userId);
}

package org.gassangaming.repository.item;

import org.gassangaming.model.item.EquippedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UnitItemsRepository extends JpaRepository<EquippedItem, EquippedItem.EquippedItemId> {

    Collection<EquippedItem> findByUnitId(long id);

    void deleteByItemId(long itemId);
}

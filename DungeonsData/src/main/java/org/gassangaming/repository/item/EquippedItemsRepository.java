package org.gassangaming.repository.item;

import org.gassangaming.model.item.EquippedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquippedItemsRepository extends JpaRepository<EquippedItem, EquippedItem.EquippedItemId> {
}

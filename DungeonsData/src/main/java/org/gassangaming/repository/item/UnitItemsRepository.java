package org.gassangaming.repository.item;

import org.gassangaming.model.item.EquippedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface UnitItemsRepository extends JpaRepository<EquippedItem, EquippedItem.EquippedItemId> {

    String FIND_BY_UNIT_ID = "SELECT i FROM EquippedItem i WHERE i.unitId=:" + PARAM_1;

    @Query(value = FIND_BY_UNIT_ID)
    Collection<EquippedItem> findByUnitId(@Param(PARAM_1) long id);

    void deleteByItemId(long itemId);
}

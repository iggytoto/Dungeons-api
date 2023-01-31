package org.gassangaming.repository.item;

import org.gassangaming.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

import static org.gassangaming.repository.Constants.PARAM_1;
import static org.gassangaming.repository.Constants.PARAM_2;

public interface ItemRepository extends JpaRepository<Item, Long> {

    String GET_ALL_UNEQUIPPED_FOR_USER = "SELECT i FROM Item i LEFT JOIN EquippedItem ei ON i.id=ei.itemId WHERE ei.itemId is null and i.userId=:" + PARAM_1;
    String GET_ALL_FOR_UNIT_ID = "SELECT i FROM Item i LEFT JOIN EquippedItem ei ON i.id=ei.itemId WHERE ei.unitId=:" + PARAM_1;
    String SET_OWNER_BY_IDS = "UPDATE Item i SET ownerId =:" + PARAM_2 + " WHERE i.id IN :" + PARAM_1;

    @Query(value = GET_ALL_UNEQUIPPED_FOR_USER)
    Collection<Item> getAllUnequippedItemsForPlayer(@Param(PARAM_1) long userId);


    @Query(value = GET_ALL_FOR_UNIT_ID)
    Collection<Item> getAllForUnit(@Param(PARAM_1) long unitId);

    @Query(value = SET_OWNER_BY_IDS)
    @Modifying
    void setOwnerByIds(@Param(PARAM_1) List<Long> toList, @Param(PARAM_2) long newOwnerId);
}

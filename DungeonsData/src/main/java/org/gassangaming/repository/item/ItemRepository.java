package org.gassangaming.repository.item;

import org.gassangaming.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface ItemRepository extends JpaRepository<Item, Long> {

    String FIND_BY_OWNER_ID_QUERY = "select u from Item u where u.userId=:" + PARAM_1;

    @Query(value = FIND_BY_OWNER_ID_QUERY)
    Collection<Item> findByOwnerId(@Param(PARAM_1) long userId);
}

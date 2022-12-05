package org.gassangaming.repository;

import org.gassangaming.model.UnitForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitForSaleRepository extends JpaRepository<UnitForSale, Long> {


    String FIND_ALL_QUERY = "SELECT * FROM units_for_sale ufs JOIN units u on u.id=ufs.unit_id";
    String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE ufs.id=:id";

    @Query(value = FIND_BY_ID_QUERY, nativeQuery = true)
    UnitForSale findById(@Param("id") long id);

    @Query(value = FIND_ALL_QUERY, nativeQuery = true)
    List<UnitForSale> findAll();
}

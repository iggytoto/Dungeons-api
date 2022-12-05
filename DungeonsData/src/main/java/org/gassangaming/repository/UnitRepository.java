package org.gassangaming.repository;

import org.gassangaming.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Modifying
    @Query(value = "UPDATE units SET owner_id =:userId WHERE id=:unitId", nativeQuery = true)
    void setOwner(@Param("unitId") long unitId, @Param("userId") long userId);

    @Query(value = "select u from Unit u where u.ownerId=:userid")
    Collection<Unit> findByOwnerIdD(@Param("userid") long userId);
}

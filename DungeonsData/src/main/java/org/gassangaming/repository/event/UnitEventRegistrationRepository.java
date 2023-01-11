package org.gassangaming.repository.event;

import org.gassangaming.model.event.UnitEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface UnitEventRegistrationRepository extends JpaRepository<UnitEventRegistration, UnitEventRegistration.UnitEventRegistrationId> {

    String FIND_ALL_BY_EVENT_ID = "SELECT uer FROM UnitEventRegistration uer WHERE uer.eventId=:" + PARAM_1;

    @Query(value = FIND_ALL_BY_EVENT_ID)
    Collection<UnitEventRegistration> findAllByEventId(@Param(PARAM_1) long eventId);
}

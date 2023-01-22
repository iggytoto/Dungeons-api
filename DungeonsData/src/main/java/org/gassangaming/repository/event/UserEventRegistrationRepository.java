package org.gassangaming.repository.event;

import org.gassangaming.model.event.UserEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface UserEventRegistrationRepository extends JpaRepository<UserEventRegistration, UserEventRegistration.UserEventRegistrationId> {

    UserEventRegistration findByUserId(@Param(PARAM_1) long userId);

    @Modifying
    void deleteByEventId(@Param(PARAM_1) long eventId);

    Collection<UserEventRegistration> findAllByEventId(long id);
}

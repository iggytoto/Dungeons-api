package org.gassangaming.repository.event;

import org.gassangaming.model.event.UserEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface UserEventRegistrationRepository extends JpaRepository<UserEventRegistration, UserEventRegistration.UserEventRegistrationId> {

    String FIND_BY_USER_ID = "SELECT uer FROM UserEventRegistration uer WHERE uer.userId =:" + PARAM_1;

    @Query(value = FIND_BY_USER_ID)
    UserEventRegistration findByUserId(@Param(PARAM_1) long userId);
}

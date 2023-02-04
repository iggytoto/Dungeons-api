package org.gassangaming.repository.event;

import org.gassangaming.model.event.UserEventInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventInstanceRepository extends JpaRepository<UserEventInstance, UserEventInstance.UserEventInstanceId> {

    List<UserEventInstance> getAllByEventId(long eventId);
}

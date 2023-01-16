package org.gassangaming.repository.event;

import org.gassangaming.model.event.UserEventInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserEventInstanceRepository extends JpaRepository<UserEventInstance, UserEventInstance.UserEventInstanceId> {
}

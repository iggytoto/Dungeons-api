package org.gassangaming.repository.event;

import org.gassangaming.model.event.EventInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventInstanceRepository extends JpaRepository<EventInstance, Long> {
}

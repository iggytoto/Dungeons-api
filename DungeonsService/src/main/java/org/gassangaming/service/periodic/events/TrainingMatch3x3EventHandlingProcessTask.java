package org.gassangaming.service.periodic.events;

import org.gassangaming.model.event.EventInstance;
import org.gassangaming.model.event.EventInstanceStatus;
import org.gassangaming.model.event.EventType;
import org.gassangaming.model.event.UserEventInstance;
import org.gassangaming.repository.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Periodic task that processes 3x3 training matches events.
 */
@Component
public class TrainingMatch3x3EventHandlingProcessTask {

    @Autowired
    UserEventRegistrationRepository userEventRegistrationRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    UserEventInstanceRepository userEventInstanceRepository;

    @Transactional()
    @Scheduled(fixedDelay = 1000)
    public void scheduledPhoenixEventHandler() {
        final var event = eventRepository.findLatestPlannedByType(EventType.TrainingMatch3x3);
        if (event == null) {
            return;
        }
        final var registeredUsers = new ArrayList<>(userEventRegistrationRepository.findAllByEventId(event.getId()));
        if (registeredUsers.size() < 2) {
            return;
        }
        while (registeredUsers.size() % 2 == 0) {
            final var userOne = registeredUsers.remove(0);
            final var userTwo = registeredUsers.remove(1);

            createInstanceForUsers(userOne.getUserId(), userTwo.getUserId(), event.getId());
        }
    }

    private void createInstanceForUsers(long userOneId, long userTwoId, long eventId) {
        final var instance = eventInstanceRepository.save(new EventInstance(eventId, EventType.TrainingMatch3x3, EventInstanceStatus.WaitingForServer));
        userEventInstanceRepository.save(new UserEventInstance(userOneId, eventId, instance.getId()));
        userEventInstanceRepository.save(new UserEventInstance(userTwoId, eventId, instance.getId()));
    }
}

package org.gassangaming.service.periodic;

import org.gassangaming.model.event.*;
import org.gassangaming.repository.event.EventInstanceRepository;
import org.gassangaming.repository.event.EventRepository;
import org.gassangaming.repository.event.UnitEventRegistrationRepository;
import org.gassangaming.repository.event.UserEventInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PhoenixRaidEventHandlingProcessTask {

    private static final String CRON_EVERYDAY_AT_NOON = "0 12 * * *";
    private static final int MAX_UNITS_ON_SINGLE_SERVER = 30;

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UnitEventRegistrationRepository unitEventRegistrationRepository;
    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    UserEventInstanceRepository userEventInstanceRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Scheduled(cron = CRON_EVERYDAY_AT_NOON)
    public void scheduledPhoenixEventHandler() {
        final var event = eventRepository.findLatestPlannedByType(EventType.PhoenixRaid);
        eventRepository.save(Event.of(EventType.PhoenixRaid, EventStatus.Planned));
        if (event == null) {
            return;
        }
        final var eventId = event.getId();
        event.setStatus(EventStatus.InProgress);
        eventRepository.save(event);
        eventRepository.flush();

        final var userIdToRosters = unitEventRegistrationRepository.findAllByEventId(eventId).stream().collect(Collectors.toMap(UnitEventRegistration::getUserId, value -> List.of(value.getUnitId()), (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).toList()));

        final var unitsPool = new ArrayList<Long>();
        final var usersPool = new ArrayList<Long>();
        for (var e : userIdToRosters.entrySet()) {
            final var userId = e.getKey();
            final var roster = e.getValue();

            if (unitsPool.size() + roster.size() > MAX_UNITS_ON_SINGLE_SERVER) {
                createEventInstanceForUsers(usersPool, eventId);
                usersPool.clear();
                unitsPool.clear();
            } else {
                unitsPool.addAll(roster);
                usersPool.add(userId);
            }
        }

    }

    private void createEventInstanceForUsers(ArrayList<Long> usersPool, long eventId) {
        final var instance = eventInstanceRepository.save(new EventInstance(eventId, EventType.PhoenixRaid, EventInstanceStatus.WaitingForServer));
        usersPool.stream().map(userId -> new UserEventInstance(userId, eventId, instance.getId())).forEach(uei -> userEventInstanceRepository.save(uei));
    }
}

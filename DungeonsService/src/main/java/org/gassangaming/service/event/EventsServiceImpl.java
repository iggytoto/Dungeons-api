package org.gassangaming.service.event;

import org.gassangaming.model.event.*;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.repository.event.EventRepository;
import org.gassangaming.repository.event.UnitEventRegistrationRepository;
import org.gassangaming.repository.event.UserEventRegistrationRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UnitEventRegistrationRepository unitEventRegistrationRepository;
    @Autowired
    UserEventRegistrationRepository userEventRegistrationRepository;
    @Autowired
    UnitRepository unitRepository;


    @Override
    public void register(Collection<Long> unitsIds, EventType eventType, long userId) throws ServiceException {
        final var event = eventRepository.findLatestPlannedByType(eventType);

        if (event == null) {
            throw new ServiceException("Current event is not exists");
        } else if (!event.getStatus().equals(EventStatus.Planned)) {
            throw new ServiceException("Event is not open and not accepting registrations");
        }
        var units = unitRepository.findAllById(unitsIds);
        if (!units.stream().allMatch(u -> u.getActivity().equals(Activity.Idle))) {
            throw new ServiceException("One or more units is not in Idle state, cannot register such roster");
        }
        var userRegistration = userEventRegistrationRepository.findByUserId(userId);
        if (userRegistration != null) {
            throw new ServiceException("Already registered on this event");
        }
        final var eventId = event.getId();
        userEventRegistrationRepository.save(new UserEventRegistration(userId, eventId));
        units.forEach(u -> {
            unitEventRegistrationRepository.save(new UnitEventRegistration(eventId, u.getId(), userId));
            unitRepository.updateActivityByUnitId(u.getId(), Activity.Event);
        });
    }

    @Override
    public Collection<Event> status(long id) {
        return eventRepository.findAllForUser(id);
    }
}

package org.gassangaming.service.event;

import org.gassangaming.model.event.*;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.event.EventInstanceRepository;
import org.gassangaming.repository.event.EventRepository;
import org.gassangaming.repository.event.UnitEventRegistrationRepository;
import org.gassangaming.repository.event.UserEventRegistrationRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.event.result.EventInstanceResult;
import org.gassangaming.service.event.resultprocess.EventInstanceResultProcessStrategy;
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
    @Autowired
    EventInstanceRepository eventInstanceRepository;
    @Autowired
    Collection<EventInstanceResultProcessStrategy> eventInstanceResultProcessStrategies;

    @Override
    public Event register(Collection<Long> unitsIds, EventType eventType, long userId) throws ServiceException {
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
        return event;
    }

    @Override
    public Collection<EventInstance> status(long userId) {
        return eventInstanceRepository.findAllByUserId(userId);
    }

    @Override
    public EventInstance applyServer(String host, String port, long userId) throws ServiceException {
        final var ei = eventInstanceRepository.findFirstLatestWaitingForServer(EventInstanceStatus.WaitingForServer);
        if (ei == null) {
            return null;
        }
        ei.setHost(host);
        ei.setPort(port);
        ei.setStatus(EventInstanceStatus.WaitingForPlayers);
        eventInstanceRepository.save(ei);
        return ei;
    }

    @Override
    public void saveResult(EventInstanceResult result, long userId) throws ServiceException {
        final var ei = eventInstanceRepository.findById(result.getEventInstanceId()).orElseThrow(() -> new ServiceException("Unknown event instance"));
        eventInstanceRepository.deleteById(ei.getId());
        userEventRegistrationRepository.deleteByEventId(ei.getEventId());
        unitEventRegistrationRepository.deleteAllByUnitId(result.getUnitsHitPoints().keySet());
        eventInstanceResultProcessStrategies.stream().filter(s -> s.getEventType().equals(result.getEventType())).findFirst().orElseThrow(() -> new ServiceException("Unknown event type")).process(result);
        final var units = unitRepository.findAllById(result.getUnitsHitPoints().keySet());
        for (Unit u : units) {
            final var hp = result.getUnitsHitPoints().get(u.getId());
            u.setActivity(hp <= 0 ? Activity.Dead : Activity.Idle);
            u.setHitPoints(hp);
        }
        unitRepository.saveAll(units);
        if (eventInstanceRepository.countByEventId(ei.getEventId()) == 0) {
            eventRepository.setClosedById(ei.getEventId());
        }
    }

    @Override
    public Collection<Unit> getEventInstanceData(long eventInstanceId) {
        return eventInstanceRepository.findAllUnitsById(eventInstanceId);
    }

    @Override
    public void cancel(long eventId, long userId) throws ServiceException {
        final var eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isEmpty()) {
            throw new ServiceException("Event is not found");
        }
        final var event = eventOpt.get();
        if (!event.getStatus().equals(EventStatus.Planned)) {
            throw new ServiceException("Cannot cancel registration on event is not in planned phase");
        }
        userEventRegistrationRepository.deleteByEventId(event.getId());
        final var unitIds = unitEventRegistrationRepository.deleteAllByUserIdAndEventId(userId, event.getId());
        for (var unitId : unitIds) {
            unitRepository.updateActivityByUnitId(unitId, Activity.Idle);
        }
    }
}

package org.gassangaming.controller;

import org.gassangaming.dto.controllers.events.EventRegisterRequestDto;
import org.gassangaming.dto.controllers.events.EventsStatusResponseDto;
import org.gassangaming.model.event.Event;
import org.gassangaming.model.event.EventStatus;
import org.gassangaming.model.event.EventType;
import org.gassangaming.model.unit.human.HumanWarrior;
import org.gassangaming.repository.event.UserEventRegistrationRepository;
import org.gassangaming.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * test case:
 * - register for event
 * - check event status for player
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerEventRegistrationCaseTest extends UseCaseTestBase {

    private static final String LOGIN = "painfedgi";
    private static final String PASSWORD = "painfedgi";

    @Autowired
    EventsController eventsController;
    @Autowired
    UserEventRegistrationRepository userEventRegistrationRepository;

    long unitId;
    long eventId;


    @Before
    public void setup() throws ServiceException {
        userId = registerUser(LOGIN, PASSWORD);
        context = login(LOGIN, PASSWORD);
        unitId = addUnit(new HumanWarrior(), userId).getId();
        eventId = addEvent(new Event(EventType.PhoenixRaid, EventStatus.Planned)).getId();
    }


    @Test
    public void testCase() {
        // registration
        final var registerDto = new EventRegisterRequestDto();
        registerDto.setEventType(EventType.PhoenixRaid);
        registerDto.setUnitsIds(List.of(unitId));
        eventsController.register(registerDto, context);
        final var registration = userEventRegistrationRepository.findByUserId(userId);
        Assert.assertNotNull(registration);
        Assert.assertEquals(userId, registration.getUserId());
        Assert.assertEquals(eventId, registration.getEventId());
        // status
        final var statusDto = (EventsStatusResponseDto) eventsController.status(context);
        Assert.assertNotNull(statusDto);
        Assert.assertEquals(1, statusDto.getEvents().size());
        final var eventInfo = statusDto.getEvents().stream().findFirst().orElseThrow();
        Assert.assertEquals(EventType.PhoenixRaid, eventInfo.getEventType());
        Assert.assertEquals(eventId, eventInfo.getId());
        Assert.assertEquals(EventStatus.Planned, eventInfo.getStatus());
    }
}

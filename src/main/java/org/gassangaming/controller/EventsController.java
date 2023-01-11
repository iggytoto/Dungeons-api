package org.gassangaming.controller;

import org.gassangaming.dto.DtoBase;
import org.gassangaming.dto.ErrorResponseDto;
import org.gassangaming.dto.OkResponseDto;
import org.gassangaming.dto.controllers.events.EventDto;
import org.gassangaming.dto.controllers.events.EventRegisterRequestDto;
import org.gassangaming.dto.controllers.events.EventsStatusResponseDto;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.event.EventsService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class EventsController {

    public static final String PATH = "/events";
    public static final String REGISTER_PATH = "/register";
    public static final String STATUS_PATH = "/status";

    @Autowired
    EventsService eventsService;

    @PostMapping(PATH + REGISTER_PATH)
    public DtoBase register(@RequestBody EventRegisterRequestDto dto, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            eventsService.register(dto.getUnitsIds(), dto.getEventType(), context.getToken().getId());
            return new OkResponseDto();
        } catch (ServiceException se) {
            return ErrorResponseDto.Of(se.getMessage());
        }
    }

    @GetMapping(PATH + STATUS_PATH)
    public DtoBase status(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return EventsStatusResponseDto.builder().events(eventsService.status(context.getToken().getId()).stream().map(EventDto::of).collect(Collectors.toList())).build();
    }


}

package org.gassangaming.controller;

import org.gassangaming.dto.*;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.matchmaking.MatchMakingService;
import org.gassangaming.model.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchMakingController {
    public static final String PATH = "/matchMaking";
    public static final String REGISTER_PATH = "/register";
    public static final String CANCEL_PATH = "/cancel";
    public static final String STATUS_PATH = "/status";
    public static final String APPLY_SERVER_PATH = "/applyServer";
    @Autowired
    MatchMakingService matchMakingService;

    @PostMapping(PATH + REGISTER_PATH)
    public DtoBase register(@RequestBody MatchMakingRegisterRequestDto request, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return ObjectResponseDto.builder().obj(matchMakingService.register(request.getRosterUnitsIds(), MatchType.MatchMaking3x3, context)).build();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + CANCEL_PATH)
    public DtoBase cancel(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            matchMakingService.cancelRegistration(context);
            return OkResponseDto.builder().build();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @GetMapping(PATH + STATUS_PATH)
    public DtoBase getStatus(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return MatchMakingGetStatusResponseDto.builder().match(matchMakingService.getStatus(context)).build();
    }

    @PostMapping(PATH + APPLY_SERVER_PATH)
    public DtoBase applyServer(@RequestBody MatchMakingServerApplyRequestDto requestDto, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return MatchMakingServerApplyResponseDto.builder().match(matchMakingService.applyServer(requestDto.getIp(), requestDto.getPort())).build();
    }

}

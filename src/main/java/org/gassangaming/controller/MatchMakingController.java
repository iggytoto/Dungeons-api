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

    @Autowired
    MatchMakingService matchMakingService;

    @PostMapping("/matchMaking/register")
    public DtoBase register(@RequestBody MatchMakingRegisterRequestDto request, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return ObjectResponseDto.builder().obj(matchMakingService.register(request.getRosterUnitsIds(), MatchType.MatchMaking3x3, context)).build();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping("/matchMaking/cancel")
    public DtoBase cancel(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            matchMakingService.cancelRegistration(context);
            return OkResponseDto.builder().build();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @GetMapping("/matchMaking/status")
    public DtoBase getStatus(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return MatchMakingGetStatusResponseDto.builder().status(matchMakingService.getStatus(context)).build();
    }

    @PostMapping("/matchMaking/applyServer")
    public DtoBase applyServer(@RequestBody MatchMakingServerApplyRequestDto requestDto, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return MatchMakingServerApplyResponseDto.builder().match(matchMakingService.applyServer(requestDto.getIp(), requestDto.getPort())).build();
    }

}

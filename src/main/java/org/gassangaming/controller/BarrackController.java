package org.gassangaming.controller;

import org.gassangaming.dto.TrainUnitRequestDto;
import org.gassangaming.dto.*;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.barrack.BarrackService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BarrackController {

    @Autowired
    BarrackService barrackService;

    @GetMapping("/barrack/availableUnits")
    public DtoBase getAvailableUnits(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return UnitListResponseDto.builder().units(barrackService.getBarrackUnits(context)).build();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping("/barrack/trainUnit")
    public DtoBase trainUnit(@RequestBody TrainUnitRequestDto request, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            barrackService.TrainUnit(request.getUnitId(), context);
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}
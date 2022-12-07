package org.gassangaming.controller;

import org.gassangaming.dto.*;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.unit.UnitService;
import org.gassangaming.service.unit.UnitServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BarrackController {

    @Autowired
    UnitService unitService;

    @GetMapping("/barrack/availableUnits")
    public DtoBase getAvailableUnits(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return GetAvailableUnitsResponse.builder().units(unitService.getBarrackUnits(context)).build();
        } catch (UnitServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping
    public DtoBase trainUnit(@RequestBody TrainUnitRequest request, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            unitService.TrainUnit(request.getUnitId(), context);
            return new OkResponseDto();
        } catch (UnitServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}

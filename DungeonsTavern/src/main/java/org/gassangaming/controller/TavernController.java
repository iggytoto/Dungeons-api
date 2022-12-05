package org.gassangaming.controller;

import org.gassangaming.dto.*;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.unit.UnitService;
import org.gassangaming.service.unit.UnitServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TavernController {

    @Autowired
    UnitService unitService;

    @GetMapping("/tavern/availableUnits")
    public DtoBase getAvailableUnits(
            @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return GetAvailableUnitsResponseDto.
                    builder().
                    units(unitService.getUnitsForSale(context)).
                    build();
        } catch (UnitServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }


    @PostMapping("/tavern/buyUnit")
    public DtoBase buyUnit(
            @RequestBody BuyUnitRequestDto buyUnitRequestDto,
            @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            unitService.buyUnit(buyUnitRequestDto.getUnitId(), context);
            return new OkResponseDto();
        } catch (UnitServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}

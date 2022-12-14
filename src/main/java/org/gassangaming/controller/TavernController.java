package org.gassangaming.controller;

import org.gassangaming.dto.BuyUnitRequestDto;
import org.gassangaming.dto.*;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.tavern.TavernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TavernController {

    @Autowired
    TavernService tavernService;

    @GetMapping("/tavern/availableUnits")
    public DtoBase getAvailableUnits(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return UnitListResponseDto.Of(tavernService.getUnitsForSale(context));
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }


    @PostMapping("/tavern/buyUnit")
    public DtoBase buyUnit(@RequestBody BuyUnitRequestDto buyUnitRequestDto, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            tavernService.buyUnit(buyUnitRequestDto.getUnitId(), context);
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}

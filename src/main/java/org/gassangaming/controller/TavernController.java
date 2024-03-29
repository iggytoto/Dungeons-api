package org.gassangaming.controller;

import org.gassangaming.dto.controllers.tavern.BuyUnitRequestDto;
import org.gassangaming.dto.*;
import org.gassangaming.dto.unit.UnitDto;
import org.gassangaming.service.tavern.UnitForSale;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.tavern.TavernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class TavernController {

    public static final String PATH = "/tavern";
    public static final String BUY_UNIT_PATH = "/buyUnit";
    public static final String GET_AVAILABLE_UNITS_PATH = "/availableUnits";
    @Autowired
    TavernService tavernService;

    @GetMapping(PATH + GET_AVAILABLE_UNITS_PATH)
    public DtoBase getAvailableUnits() {
        return ListResponseDto.of(UnitForSale.All().stream().map(UnitDto::of).collect(Collectors.toList()));
    }

    @PostMapping(PATH + BUY_UNIT_PATH)
    @Transactional
    public DtoBase buyUnit(@RequestBody BuyUnitRequestDto buyUnitRequestDto, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            return UnitDto.of(tavernService.buyUnit(buyUnitRequestDto.getType(), context.getToken().getUserId()));
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}

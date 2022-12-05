package org.gassangaming.controller;

import org.gassangaming.dto.DtoBase;
import org.gassangaming.dto.ErrorResponseDto;
import org.gassangaming.dto.GetAvailableUnitsResponse;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.unit.UnitService;
import org.gassangaming.service.unit.UnitServiceException;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    private static UserContext extractContext(HttpServletRequest request) {
        return (UserContext) request.getAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME);
    }
}

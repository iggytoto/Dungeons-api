package org.gassangaming.controller;

import org.gassangaming.dto.*;
import org.gassangaming.dto.equip.UnitEquipDto;
import org.gassangaming.dto.equip.UpgradeUnitEquipmentRequestDto;
import org.gassangaming.dto.unit.UnitDto;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.barrack.BarrackService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class BarrackController {
    public static final String PATH = "/barrack";
    public static final String GET_AVAILABLE_UNITS_PATH = "/availableUnits";
    public static final String TRAIN_UNIT_PATH = "/trainUnit";
    public static final String CHANGE_UNIT_NAME_PATH = "/changeUnitName";
    public static final String CHANGE_UNIT_BATTLE_BEHAVIOR_PATH = "/changeUnitBattleBehavior";
    public static final String UPGRADE_UNIT_EQUIPMENT_PATH = "/upgradeUnitEquipment";

    @Autowired
    BarrackService barrackService;

    @GetMapping(PATH + GET_AVAILABLE_UNITS_PATH)
    public DtoBase getAvailableUnits(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return UnitListResponseDto.builder().units(barrackService.getBarrackUnits(context).stream().map(UnitDto::of).collect(Collectors.toList())).build();
    }

    @PostMapping(PATH + TRAIN_UNIT_PATH)
    public DtoBase trainUnit(@RequestBody TrainUnitRequestDto request, @RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        try {
            barrackService.TrainUnit(request.getUnitId(), context);
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + CHANGE_UNIT_NAME_PATH)
    public DtoBase changeUnitName(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody ChangeUnitNameRequestDto request) {
        try {
            barrackService.ChangeUnitName(request.getUnitId(), request.getNewName(), context);
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + CHANGE_UNIT_BATTLE_BEHAVIOR_PATH)
    public DtoBase changeUnitBehavior(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody ChangeUnitBattleBehaviorRequestDto request) {
        try {
            barrackService.ChangeUnitBattleBehavior(request.getUnitId(), request.getNewBattleBehavior(), context);
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + UPGRADE_UNIT_EQUIPMENT_PATH)
    public DtoBase upgradeUnitEquipment(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody UpgradeUnitEquipmentRequestDto request) {
        try {
            final var eq = barrackService.UpgradeUnitEquipment(request.getEquipmentId(), request.getUnitType(), request.getParamNameToUpgrade(), context);
            return UnitEquipDto.of(eq);
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}

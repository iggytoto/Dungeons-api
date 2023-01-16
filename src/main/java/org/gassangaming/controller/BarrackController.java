package org.gassangaming.controller;

import org.gassangaming.dto.*;
import org.gassangaming.dto.items.ItemDto;
import org.gassangaming.dto.skills.UnitSkillsDto;
import org.gassangaming.dto.skills.UpgradeUnitSkillsRequestDto;
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
    public static final String GET_AVAILABLE_ITEMS_PATH = "/availableItems";
    public static final String EQUIP_ITEM_PATH = "/equip";
    public static final String UNEQUIP_ITEM_PATH = "/unEquip";
    public static final String CHANGE_UNIT_NAME_PATH = "/changeUnitName";
    public static final String CHANGE_UNIT_BATTLE_BEHAVIOR_PATH = "/changeUnitBattleBehavior";
    public static final String UPGRADE_UNIT_skillsMENT_PATH = "/upgradeUnitSkills";

    @Autowired
    BarrackService barrackService;

    @GetMapping(PATH + GET_AVAILABLE_UNITS_PATH)
    public DtoBase getAvailableUnits(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return ListResponseDto.builder().items(barrackService.getBarrackUnits(context.getToken().getUserId()).stream().map(UnitDto::of).collect(Collectors.toList())).build();
    }

    @GetMapping(PATH + GET_AVAILABLE_ITEMS_PATH)
    public DtoBase getAvailableItems(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context) {
        return ListResponseDto.builder().items(barrackService.getStoredItems(context).stream().map(ItemDto::of).collect(Collectors.toList())).build();
    }

    @PostMapping(PATH + EQUIP_ITEM_PATH)
    public DtoBase equipItem(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody EquipItemRequestDto dto) {
        try {
            barrackService.equipItem(dto.getItemId(), dto.getUnitId(), context);
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + UNEQUIP_ITEM_PATH)
    public DtoBase unEquipItem(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody UnEquipItemRequestDto dto) {
        try {
            barrackService.unEquipItem(dto.getItemId(), context);
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

    @PostMapping(PATH + UPGRADE_UNIT_skillsMENT_PATH)
    public DtoBase upgradeUnitEquipment(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody UpgradeUnitSkillsRequestDto request) {
        try {
            final var eq = barrackService.UpgradeUnitEquipment(request.getSkillsId(), request.getUnitType(), request.getParamNameToUpgrade(), context);
            return UnitSkillsDto.of(eq);
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }
}

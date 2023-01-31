package org.gassangaming.controller;

import org.gassangaming.dto.DtoBase;
import org.gassangaming.dto.ErrorResponseDto;
import org.gassangaming.dto.ListResponseDto;
import org.gassangaming.dto.OkResponseDto;
import org.gassangaming.dto.controllers.dungeon.*;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.dungeon.DungeonService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class DungeonController {

    public static final String PATH = "/dungeon";
    public static final String GET_ALL_PATH = "/getAll";
    public static final String CREATE_EXPEDITION_PATH = "/createExpedition";
    public static final String RETURN_EXPEDITION_PATH = "/returnExpedition";
    public static final String MOVE_EXPEDITION_PATH = "/moveExpedition";


    @Autowired
    DungeonService dungeonService;

    @GetMapping(PATH + GET_ALL_PATH)
    @Transactional
    public DtoBase getDungeons() {
        return ListResponseDto.of(dungeonService.getAllActiveDungeons().stream().map(DungeonInstanceDto::of).toList());
    }

    @PostMapping(PATH + CREATE_EXPEDITION_PATH)
    @Transactional
    public DtoBase create(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody CreateExpeditionRequestDto dto) {
        try {
            return DungeonExpeditionDto.of(dungeonService.createExpedition(dto.getRoster(), dto.getStartingRoomId(), dto.getDungeonInstanceId(), context.getToken().getUserId()));
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + RETURN_EXPEDITION_PATH)
    @Transactional
    public DtoBase returnExpedition(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody ReturnExpeditionRequestDto dto) {
        try {
            dungeonService.returnExpeditionToTown(dto.getExpeditionId(), context.getToken().getUserId());
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + MOVE_EXPEDITION_PATH)
    @Transactional
    public DtoBase moveExpedition(@RequestAttribute(UserContext.CONTEXT_ATTRIBUTE_NAME) UserContext context, @RequestBody MoveExpeditionRequestDto dto) {
        try {
            dungeonService.moveExpeditionToRoom(dto.getTargetRoomId(), dto.getExpeditionId(), context.getToken().getUserId());
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

}

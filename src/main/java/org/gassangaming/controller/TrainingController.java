package org.gassangaming.controller;

import org.gassangaming.dto.*;
import org.gassangaming.model.MatchResult;
import org.gassangaming.model.Unit;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.training.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

    public static final String PATH = "/training";
    public static final String GET_ROSTER_FOR_USER_PATH = "/getRosterForUser";
    public static final String SAVE_TRAINING_RESULT_PATH = "/saveTrainingResult";
    public static final String SAVE_ROSTERS_PATH = "/saveRosters";

    @Autowired
    TrainingService trainingService;

    @GetMapping(PATH + GET_ROSTER_FOR_USER_PATH)
    public DtoBase getRosterForUser(@RequestBody UserIdRequestDto request) {
        try {
            return UnitListResponseDto.builder().units(trainingService.getTrainingRosterForUser(request.getUserId())).build();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

    @PostMapping(PATH + SAVE_ROSTERS_PATH)
    public DtoBase saveRosters(@RequestBody UnitListRequestDto<Unit> units) {
        try {
            trainingService.saveRosters(units.getUnits());
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }

    }

    @PostMapping(PATH + SAVE_TRAINING_RESULT_PATH)
    public DtoBase saveTrainingResult(@RequestBody SaveTrainingResultRequestDto request) {
        try {
            trainingService.saveTrainingResult(MatchResult.Of(request.getUserOneId(), request.getUserTwoId(), request.getWinnerId(), request.getMatchType(), request.getDate()));
            return new OkResponseDto();
        } catch (ServiceException e) {
            return ErrorResponseDto.Of(e.getMessage());
        }
    }

}

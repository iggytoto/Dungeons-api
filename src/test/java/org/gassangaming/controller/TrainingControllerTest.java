package org.gassangaming.controller;

import org.gassangaming.dto.SaveTrainingResultRequestDto;
import org.gassangaming.dto.ListResponseDto;
import org.gassangaming.dto.UserIdRequestDto;
import org.gassangaming.dto.unit.UnitDto;
import org.gassangaming.model.MatchType;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.model.unit.TrainingUnit;
import org.gassangaming.model.unit.human.HumanWarrior;
import org.gassangaming.repository.TrainingUnitRepository;
import org.gassangaming.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingControllerTest extends UseCaseTestBase {

    private final static Date DATE = new Date();

    private long u1Id;
    private long u2Id;
    private long u3Id;

    @Autowired
    TrainingUnitRepository trainingUnitRepository;

    @Autowired
    TrainingController trainingController;

    @Before
    public void setup() {
        try {
            registerDefaultUser();
        } catch (ServiceException ignored) {
        }
        u1Id = addUnit(HumanWarrior.of(Activity.Training), userId).getId();
        u2Id = addUnit(HumanWarrior.of(Activity.Training), userId).getId();
        u3Id = addUnit(HumanWarrior.of(Activity.Training), userId).getId();
        final var trainingUnits = new ArrayList<TrainingUnit>();
        trainingUnits.add(TrainingUnit.Of(userId, u1Id));
        trainingUnits.add(TrainingUnit.Of(userId, u2Id));
        trainingUnits.add(TrainingUnit.Of(userId, u3Id));
        trainingUnitRepository.saveAll(trainingUnits);
    }

    @Test
    public void getRosterForUser() {
        final var requestDto = new UserIdRequestDto();
        requestDto.setUserId(userId);
        final var result = ((ListResponseDto<UnitDto>) trainingController.getRosterForUser(requestDto)).getUnits();
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.stream().anyMatch(u -> u.getId() == u1Id));
        Assert.assertTrue(result.stream().anyMatch(u -> u.getId() == u2Id));
        Assert.assertTrue(result.stream().anyMatch(u -> u.getId() == u3Id));
    }

    @Test
    public void saveTrainingResult() {
        final var requestDto = new SaveTrainingResultRequestDto();
        requestDto.setDate(DATE);
        requestDto.setMatchType(MatchType.MatchMaking3x3);
        requestDto.setUserOneId(1);
        requestDto.setUserTwoId(2);
        requestDto.setWinnerUserId(1);
        final var unitState = new ArrayList<UnitDto>();
        unitState.add(UnitDto.of(HumanWarrior.of(u1Id, Activity.Training)));
        unitState.add(UnitDto.of(HumanWarrior.of(u2Id, Activity.Training)));
        unitState.add(UnitDto.of(HumanWarrior.of(u3Id, Activity.Training)));
        requestDto.setUnitsState(unitState);
        trainingController.saveTrainingResult(requestDto);
        final var ids = new ArrayList<Long>();
        ids.add(u1Id);
        ids.add(u2Id);
        ids.add(u3Id);
        final var unitsAfterTraining = unitRepository.findAllById(ids);
        Assert.assertTrue(unitsAfterTraining.stream().allMatch(u -> u.getActivity() == Activity.Idle));
    }
}
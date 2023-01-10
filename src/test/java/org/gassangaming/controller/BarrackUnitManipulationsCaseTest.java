package org.gassangaming.controller;

import org.gassangaming.dto.ChangeUnitBattleBehaviorRequestDto;
import org.gassangaming.dto.ChangeUnitNameRequestDto;
import org.gassangaming.dto.ListResponseDto;
import org.gassangaming.dto.unit.UnitDto;
import org.gassangaming.model.unit.BattleBehavior;
import org.gassangaming.model.unit.human.HumanWarrior;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test case:
 * - Get available units from barrack
 * - change unit name
 * - change unit battle behavior
 * - set unit to training
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BarrackUnitManipulationsCaseTest extends UseCaseTestBase {

    private final static String NEW_NAME = "NEW_NAME";

    private final static String LOGIN = "asdasd1d1d1d";
    private final static String PASSWORD = "asdasd1d1d1d";

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    BarrackController barrackController;


    @Before
    public void setup() throws ServiceException {
        userId = registerUser(LOGIN, PASSWORD);
        context = login(LOGIN, PASSWORD);
        final var unit1 = new HumanWarrior();
        unit1.setOwnerId(userId);
        unitRepository.save(unit1);
        final var unit2 = new HumanWarrior();
        unit2.setOwnerId(userId);
        unitRepository.save(unit2);
        final var unit3 = new HumanWarrior();
        unit3.setOwnerId(userId);
        unitRepository.save(unit3);
    }

    @Test
    public void testCase() {
        final var units = ((ListResponseDto<UnitDto>) barrackController.getAvailableUnits(context)).getUnits();
        Assert.assertEquals(3, units.size());
        final var unitToChange = units.stream().findFirst().orElseThrow();
        //name change test
        final var changeUnitNameRequestDto = new ChangeUnitNameRequestDto();
        changeUnitNameRequestDto.setUnitId(unitToChange.getId());
        changeUnitNameRequestDto.setNewName(NEW_NAME);
        barrackController.changeUnitName(context, changeUnitNameRequestDto);
        final var changedUnitName = unitRepository.findById(unitToChange.getId()).orElseThrow();
        Assert.assertEquals(NEW_NAME, changedUnitName.getName());
        //battle behavior change test
        final var changeUnitBbRequestDto = new ChangeUnitBattleBehaviorRequestDto();
        changeUnitBbRequestDto.setUnitId(unitToChange.getId());
        changeUnitBbRequestDto.setNewBattleBehavior(BattleBehavior.GuardNearestAlly);
        barrackController.changeUnitBehavior(context, changeUnitBbRequestDto);
        final var changedUnitBb = unitRepository.findById(unitToChange.getId()).orElseThrow();
        Assert.assertEquals(BattleBehavior.GuardNearestAlly, changedUnitBb.getBattleBehavior());
    }
}

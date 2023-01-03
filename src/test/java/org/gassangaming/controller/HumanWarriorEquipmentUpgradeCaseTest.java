package org.gassangaming.controller;

import org.gassangaming.dto.equip.UpgradeUnitEquipmentRequestDto;
import org.gassangaming.model.euqipment.human.HumanWarriorEquipment;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.model.unit.human.HumanWarrior;
import org.gassangaming.repository.unit.equip.HumanWarriorEquipmentRepository;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.tavern.TavernService;
import org.gassangaming.service.unit.equipment.HumanWarriorUnitEquipmentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test case:
 * - upgrade Human warrior equip to 1,0
 * - upgrade Human warrior equip to 1,1
 * - upgrade Human warrior equip to 1,2
 * - upgrade Human warrior equip to 2,2
 * - upgrade Human warrior equip to 3,2
 * - upgrade Human warrior equip to 4,2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HumanWarriorEquipmentUpgradeCaseTest extends UseCaseTestBase {

    @Autowired
    HumanWarriorEquipmentRepository equipmentRepository;

    @Autowired
    BarrackController barrackController;
    @Autowired
    TavernService tavernService;

    HumanWarrior unit;

    @Before
    public void setup() throws ServiceException {
        registerDefaultUser();
        loginAsDefaultUser();
        unit = (HumanWarrior) tavernService.buyUnit(UnitType.HumanWarrior, context);
    }

    @Test
    public void testCase() {
        final var eqId = equipmentRepository.getByUnitId(unit.getId()).getId();
        final var upgradeRequestDto = new UpgradeUnitEquipmentRequestDto();
        upgradeRequestDto.setEquipmentId(eqId);
        upgradeRequestDto.setUnitType(UnitType.HumanWarrior);
        upgradeRequestDto.setParamNameToUpgrade(HumanWarriorUnitEquipmentServiceImpl.DefenceParamName);
        barrackController.upgradeUnitEquipment(context, upgradeRequestDto);
        assertEquipmentOnState(equipmentRepository.getByUnitId(unit.getId()), 1, 0);
        upgradeRequestDto.setParamNameToUpgrade(HumanWarriorUnitEquipmentServiceImpl.OffenceParamName);
        barrackController.upgradeUnitEquipment(context, upgradeRequestDto);
        assertEquipmentOnState(equipmentRepository.getByUnitId(unit.getId()), 1, 1);
        upgradeRequestDto.setParamNameToUpgrade(HumanWarriorUnitEquipmentServiceImpl.OffenceParamName);
        barrackController.upgradeUnitEquipment(context, upgradeRequestDto);
        assertEquipmentOnState(equipmentRepository.getByUnitId(unit.getId()), 1, 2);
        upgradeRequestDto.setParamNameToUpgrade(HumanWarriorUnitEquipmentServiceImpl.DefenceParamName);
        barrackController.upgradeUnitEquipment(context, upgradeRequestDto);
        assertEquipmentOnState(equipmentRepository.getByUnitId(unit.getId()), 2, 2);
        upgradeRequestDto.setParamNameToUpgrade(HumanWarriorUnitEquipmentServiceImpl.DefenceParamName);
        barrackController.upgradeUnitEquipment(context, upgradeRequestDto);
        assertEquipmentOnState(equipmentRepository.getByUnitId(unit.getId()), 3, 2);
        upgradeRequestDto.setParamNameToUpgrade(HumanWarriorUnitEquipmentServiceImpl.DefenceParamName);
        barrackController.upgradeUnitEquipment(context, upgradeRequestDto);
        assertEquipmentOnState(equipmentRepository.getByUnitId(unit.getId()), 4, 2);
    }

    private void assertEquipmentOnState(HumanWarriorEquipment eq, int expectedDefence, int expectedOffence) {
        Assert.assertEquals(expectedDefence, eq.getDefencePoints());
        Assert.assertEquals(expectedOffence, eq.getOffencePoints());
    }
}
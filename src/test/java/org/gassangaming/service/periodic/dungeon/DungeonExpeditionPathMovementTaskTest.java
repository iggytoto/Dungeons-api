package org.gassangaming.service.periodic.dungeon;

import org.gassangaming.controller.UseCaseTestBase;
import org.gassangaming.model.dungeon.DungeonExpedition;
import org.gassangaming.model.dungeon.DungeonInstance;
import org.gassangaming.model.dungeon.DungeonPath;
import org.gassangaming.model.dungeon.DungeonRoom;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.human.HumanArcher;
import org.gassangaming.model.unit.human.HumanCleric;
import org.gassangaming.model.unit.human.HumanSpearman;
import org.gassangaming.repository.dungeon.DungeonInstanceExpeditionLocationRepository;
import org.gassangaming.repository.dungeon.DungeonInstanceRepository;
import org.gassangaming.repository.dungeon.DungeonPathRepository;
import org.gassangaming.repository.dungeon.DungeonRoomRepository;
import org.gassangaming.service.dungeon.DungeonService;
import org.gassangaming.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DungeonExpeditionPathMovementTaskTest extends UseCaseTestBase {

    private static final String LOGIN = "naoihegrnoia";
    private static final String PASSWORD = "naoihegrnoia";

    @Autowired
    DungeonExpeditionPathMovementTask task;
    @Autowired
    DungeonInstanceRepository dungeonInstanceRepository;
    @Autowired
    DungeonPathRepository dungeonPathRepository;
    @Autowired
    DungeonRoomRepository dungeonRoomRepository;
    @Autowired
    DungeonService dungeonService;
    @Autowired
    DungeonInstanceExpeditionLocationRepository dungeonInstanceExpeditionLocationRepository;
    private DungeonExpedition expedition;
    private DungeonRoom destinationRoom;

    @Before
    public void setup() throws ServiceException {
        //simple dungeon
        final var dungeonInstance = dungeonInstanceRepository.save(new DungeonInstance());
        destinationRoom = dungeonRoomRepository.save(new DungeonRoom(true, dungeonInstance));
        final var startRoom = dungeonRoomRepository.save(new DungeonRoom(true, dungeonInstance));
        final var path = dungeonPathRepository.save(new DungeonPath(startRoom.getId(), destinationRoom.getId(), dungeonInstance, .001f));
        // setting up user and units
        final var roster = new ArrayList<Unit>();
        userId = registerUser(LOGIN, PASSWORD);
        roster.add(addUnit(new HumanArcher(), userId));
        roster.add(addUnit(new HumanCleric(), userId));
        roster.add(addUnit(new HumanSpearman(), userId));
        // registering
        expedition = dungeonService.createExpedition(roster.stream().map(Unit::getId).toList(), startRoom.getId(), dungeonInstance.getId(), userId);
        // moving roster
        dungeonService.moveExpeditionToRoom(path.getId(), expedition.getId(), userId);
    }

    @Test
    public void testCase() {
        task.scheduleExpeditionsMovement();
        final var resultLocation = dungeonInstanceExpeditionLocationRepository.findById(expedition.getId()).orElseThrow();
        Assert.assertTrue(resultLocation.isRoom());
        Assert.assertEquals(destinationRoom.getId(), resultLocation.getLocationId());
        Assert.assertEquals(expedition.getId(), resultLocation.getExpeditionId());
    }
}
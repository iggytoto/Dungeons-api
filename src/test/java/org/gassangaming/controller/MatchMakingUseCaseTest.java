package org.gassangaming.controller;

import org.gassangaming.dto.MatchMakingGetStatusResponseDto;
import org.gassangaming.dto.MatchMakingRegisterRequestDto;
import org.gassangaming.dto.MatchMakingServerApplyRequestDto;
import org.gassangaming.dto.ObjectResponseDto;
import org.gassangaming.model.Match;
import org.gassangaming.model.MatchStatus;
import org.gassangaming.model.unit.human.HumanWarrior;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;

/**
 * Match making case:
 * - User1 registration
 * - User1 cancel
 * - User2 registration
 * - User2 status check
 * - User1 registration
 * - Server registration
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchMakingUseCaseTest extends UseCaseTestBase {

    private static final String USER_1_NAME = "asdasd";
    private static final String USER_1_PASS = "asdasd";
    private static final String USER_2_NAME = "asdasd2222";
    private static final String USER_2_PASS = "asdasd";
    private static final String SERVER_NAME = "asdasddadad";
    private static final String SERVER_PASS = "asdasd";

    @Autowired
    MatchMakingController matchMakingController;

    private final ArrayList<Long> user1Units = new ArrayList<>();
    private final ArrayList<Long> user2Units = new ArrayList<>();

    private UserContext user1Context;
    private UserContext user2Context;
    private UserContext serverContext;


    @Before
    public void setup() throws ServiceException {
        long serverId = registerServerUser(SERVER_NAME, SERVER_PASS);
        long user1Id = registerUser(USER_1_NAME, USER_1_PASS);
        long user2Id = registerUser(USER_2_NAME, USER_2_PASS);
        user1Units.add(addUnit(new HumanWarrior(), user1Id));
        user1Units.add(addUnit(new HumanWarrior(), user1Id));
        user1Units.add(addUnit(new HumanWarrior(), user1Id));
        user2Units.add(addUnit(new HumanWarrior(), user2Id));
        user2Units.add(addUnit(new HumanWarrior(), user2Id));
        user2Units.add(addUnit(new HumanWarrior(), user2Id));
        user1Context = login(USER_1_NAME, USER_1_PASS);
        user2Context = login(USER_2_NAME, USER_2_PASS);
        serverContext = login(SERVER_NAME, SERVER_PASS);
    }

    @Test
    public void useCase() {
        //user 1 register
        final var user1matchMakingRegisterRequestDto = new MatchMakingRegisterRequestDto();
        user1matchMakingRegisterRequestDto.setRosterUnitsIds(user1Units);
        final var user1Req = ((ObjectResponseDto<Match>) matchMakingController.register(user1matchMakingRegisterRequestDto, user1Context)).getObj();
        Assert.notNull(user1Req, "Should be match presented");
        //user 1 cancel
        matchMakingController.cancel(user1Context);
        //user 2 register
        final var user2matchMakingRegisterRequestDto = new MatchMakingRegisterRequestDto();
        user2matchMakingRegisterRequestDto.setRosterUnitsIds(user2Units);
        final var user2Req = ((ObjectResponseDto<Match>) matchMakingController.register(user2matchMakingRegisterRequestDto, user2Context)).getObj();
        Assert.notNull(user2Req, "Should be match presented");
        //status check
        final var user2Status = ((MatchMakingGetStatusResponseDto) matchMakingController.getStatus(user2Context)).getMatch();
        Assert.notNull(user2Status, "Should be match presented");
        org.junit.Assert.assertEquals(MatchStatus.Searching, user2Status.getStatus());
        //user 1 registration
        matchMakingController.register(user1matchMakingRegisterRequestDto, user1Context);
        //status check
        final var user1Status = ((MatchMakingGetStatusResponseDto) matchMakingController.getStatus(user1Context)).getMatch();
        Assert.notNull(user1Status, "Should be match presented");
        org.junit.Assert.assertEquals(MatchStatus.PlayersFound, user1Status.getStatus());
        //server registration
        final var serverRequest = new MatchMakingServerApplyRequestDto();
        matchMakingController.applyServer(serverRequest, serverContext);
        //status check
        final var user2Status2 = ((MatchMakingGetStatusResponseDto) matchMakingController.getStatus(user2Context)).getMatch();
        Assert.notNull(user1Status, "Should be match presented");
        org.junit.Assert.assertEquals(MatchStatus.ServerFound, user2Status2.getStatus());

    }
}

package org.gassangaming.controller;


import org.gassangaming.model.Account;
import org.gassangaming.model.unit.Unit;
import org.gassangaming.repository.AccountRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.repository.UserRepository;
import org.gassangaming.service.Role;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.auth.AuthService;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

public class UseCaseTestBase {

    protected final static String LOGIN = "HAHAHAHAHAH";
    protected final static String PASSWORD = "12312515sdfwdfwef_2rffs";

    protected long userId;

    protected UserContext context;

    @Autowired
    AuthService authService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UnitRepository unitRepository;


    protected long registerServerUser(String login, String password) throws ServiceException {
        final var id = registerUser(login, password);
        final var u = userRepository.findById(id).orElseThrow();
        u.setRole(Role.Server);
        userRepository.save(u);
        return id;
    }

    protected <T extends Unit> T addUnit(T u, long userId) {
        u.setOwnerId(userId);
        return unitRepository.save(u);
    }

    protected Unit addUnit(Unit u) {
        return unitRepository.save(u);
    }

    protected long registerUser(String login, String password) throws ServiceException {
        final var userId = authService.register(login, password);
        final var account = new Account();
        account.setUserId(userId);
        account.setGoldAmount(99999999);
        accountRepository.save(account);
        return userId;
    }

    protected void registerDefaultUser() throws ServiceException {
        userId = registerUser(LOGIN, PASSWORD);
    }


    protected UserContext login(String login, String password) throws ServiceException {
        return UserContext.builder().token(authService.login(login, password)).build();
    }

    protected void loginAsDefaultUser() throws ServiceException {
        context = login(LOGIN, PASSWORD);
    }
}

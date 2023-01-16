package org.gassangaming.service.account;

import org.gassangaming.model.Account;
import org.gassangaming.model.Valuable;
import org.gassangaming.repository.AccountRepository;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void buyItem(Valuable v, long userId) throws ServiceException {
        var a = accountRepository.findByUserId(userId);
        if (isAccountEnoughFor(a, v)) {
            a.setGoldAmount(a.getGoldAmount() - v.getGoldCost());
            accountRepository.save(a);
        } else {
            throw new ServiceException("Account has not enough to buy this.");
        }
    }

    private boolean isAccountEnoughFor(Account a, Valuable v) {
        return a.getGoldAmount() >= v.getGoldCost();
    }
}

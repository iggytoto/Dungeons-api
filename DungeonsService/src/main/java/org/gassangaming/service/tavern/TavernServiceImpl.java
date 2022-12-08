package org.gassangaming.service.tavern;

import org.gassangaming.model.UnitForSale;
import org.gassangaming.repository.AccountRepository;
import org.gassangaming.repository.UnitForSaleRepository;
import org.gassangaming.repository.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.util.CostUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TavernServiceImpl implements TavernService {

    @Autowired
    UnitForSaleRepository unitForSaleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UnitRepository unitRepository;

    @Override
    public Collection<UnitForSale> getUnitsForSale(UserContext context) {
        return unitForSaleRepository.findAll();
    }

    @Override
    public void buyUnit(long unitId, UserContext context) throws ServiceException {
        final var unitForSale = unitForSaleRepository.findById(unitId);
        final var playerAccount = accountRepository.findByUserId(context.getToken().getUserId());
        if (CostUtility.isAccountHasEnoughFor(playerAccount, unitForSale)) {
            CostUtility.reduceAccountOn(playerAccount, unitForSale);
            unitForSaleRepository.delete(unitForSale);
            unitRepository.setOwner(unitForSale.getUnitId(), context.getToken().getUserId());
            accountRepository.save(playerAccount);
        } else {
            throw new ServiceException("Player account has not enough to buy this.");
        }
    }



}

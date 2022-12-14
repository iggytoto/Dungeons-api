package org.gassangaming.service.tavern;

import org.gassangaming.model.unit.Unit;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.account.AccountService;
import org.gassangaming.service.exception.ServiceException;
import org.gassangaming.service.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TavernServiceImpl implements TavernService {

    @Autowired
    UnitService unitService;
    @Autowired
    AccountService accountService;

    @Override
    public Unit buyUnit(UnitType type, UserContext context) throws ServiceException {
        final var unitForSale = UnitForSale.Of(type);
        unitForSale.getUnit().setId(null);
        unitForSale.getUnit().setOwnerId(null);
        accountService.buyItem(unitForSale, context);
        return unitService.createNewUnit(unitForSale.getUnit(), context);
    }
}

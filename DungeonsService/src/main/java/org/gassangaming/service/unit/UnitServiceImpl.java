package org.gassangaming.service.unit;

import org.gassangaming.model.Activity;
import org.gassangaming.model.Unit;
import org.gassangaming.model.UnitForSale;
import org.gassangaming.repository.AccountRepository;
import org.gassangaming.repository.UnitForSaleRepository;
import org.gassangaming.repository.UnitRepository;
import org.gassangaming.service.UserContext;
import org.gassangaming.util.CostUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UnitServiceImpl implements UnitService {

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
    public Collection<Unit> getBarrackUnits(UserContext context) {
        return unitRepository.findByOwnerIdD(context.getToken().getUserId());
    }

    @Override
    public void buyUnit(long unitId, UserContext context) throws UnitServiceException {
        final var unitForSale = unitForSaleRepository.findById(unitId);
        final var playerAccount = accountRepository.findByUserId(context.getToken().getUserId());
        if (CostUtility.isAccountHasEnoughFor(playerAccount, unitForSale)) {
            CostUtility.reduceAccountOn(playerAccount, unitForSale);
            unitForSaleRepository.delete(unitForSale);
            unitRepository.setOwner(unitForSale.getUnitId(), context.getToken().getUserId());
            accountRepository.save(playerAccount);
        } else {
            throw new UnitServiceException("Player account has not enough to buy this.");
        }
    }

    @Override
    public void TrainUnit(long unitId, UserContext context) throws UnitServiceException {
        final var unitToTrain = unitRepository.findById(unitId);
        checkRights(unitToTrain, context);
        if (!Activity.Idle.equals(unitToTrain.getActivity())) {
            throw new UnitServiceException("Unit is busy already");
        }
        unitToTrain.setActivity(Activity.Training);
        unitRepository.save(unitToTrain);
    }

    private void checkRights(Unit u, UserContext context) throws UnitServiceException {
        if (u == null) {
            throw new UnitServiceException("Unit not found");
        }
        if (context.getToken().getUserId() != u.getOwnerId()) {
            throw new UnitServiceException("You dont have right to train not your units");
        }
    }
}

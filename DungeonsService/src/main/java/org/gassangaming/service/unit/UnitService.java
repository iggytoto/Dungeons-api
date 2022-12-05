package org.gassangaming.service.unit;

import org.gassangaming.model.Unit;
import org.gassangaming.model.UnitForSale;
import org.gassangaming.service.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public interface UnitService {

    Collection<UnitForSale> getUnitsForSale(UserContext context) throws UnitServiceException;

    Collection<Unit> getBarrackUnits(UserContext context) throws UnitServiceException;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    void buyUnit(long unitId, UserContext context) throws UnitServiceException;
}

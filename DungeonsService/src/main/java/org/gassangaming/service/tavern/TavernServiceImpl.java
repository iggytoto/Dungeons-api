package org.gassangaming.service.tavern;

import org.gassangaming.model.euqipment.UnitEquipHelper;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.repository.AccountRepository;
import org.gassangaming.repository.UnitEquipRepository;
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
    AccountRepository accountRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    Collection<UnitEquipRepository> equipRepositories;

    @Override
    public void buyUnit(UnitType type, UserContext context) throws ServiceException {
        final var unitForSale = UnitForSale.Of(type);
        final var playerAccount = accountRepository.findByUserId(context.getToken().getUserId());
        if (CostUtility.isAccountHasEnoughFor(playerAccount, unitForSale)) {
            CostUtility.reduceAccountOn(playerAccount, unitForSale);
            final var unitToSave = unitForSale.getUnit();
            unitToSave.setOwnerId(context.getToken().getUserId());
            final var savedUnit = unitRepository.save(unitToSave);
            final var equip = UnitEquipHelper.getDefaultInstanceFor(savedUnit);
            if (equip != null) {
                equip.setUnitId(savedUnit.getId());
                final var equipRepo = equipRepositories.stream().filter(r -> r.getTargetType().equals(equip.getClass())).findFirst().orElseThrow();
                equipRepo.save(equip);
            }
            accountRepository.save(playerAccount);
        } else {
            throw new ServiceException("Player account has not enough to buy this.");
        }
    }


}

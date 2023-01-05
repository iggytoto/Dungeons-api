package org.gassangaming.service.unit.equipment;

import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Concrete equipment service specification.
 *
 * @param <T> - concrete type of unit equipment
 */
@Service
public interface UnitEquipmentService<T extends UnitEquip> {

    /**
     * @return concrete class that this service process
     */
    Class<T> getTargetEquipClass();

    /**
     * @return concrete unit type to which unit equipment refer to
     */
    UnitType getTargetUnitType();

    @Transactional
    T upgrade(long eqId, String paramNameToUpgrade, UserContext context) throws ServiceException;

    @Transactional
    T saveOrUpdate(T e);

    @Transactional
    default T saveOrUpdateEquip(UnitEquip e) throws ServiceException {
        if (!getTargetEquipClass().isInstance(e)) {
            throw new ServiceException("given equipment is not supported by the service");
        }
        return saveOrUpdate((T) e);
    }

    T getByUnitId(long id);
}

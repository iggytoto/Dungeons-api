package org.gassangaming.service.unit.skills;

import org.gassangaming.model.euqipment.UnitSkills;
import org.gassangaming.model.unit.UnitType;
import org.gassangaming.service.UserContext;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Concrete skills service specification.
 *
 * @param <T> - concrete type of unit skills
 */
@Service
public interface UnitSkillsService<T extends UnitSkills> {

    /**
     * @return concrete class that this service process
     */
    Class<T> getTargetEquipClass();

    /**
     * @return concrete unit type to which unit skill refer to
     */
    UnitType getTargetUnitType();

    @Transactional
    T upgrade(long eqId, String paramNameToUpgrade, UserContext context) throws ServiceException;

    @Transactional
    T saveOrUpdate(T e);

    @Transactional
    default T saveOrUpdateEquip(UnitSkills e) throws ServiceException {
        if (!getTargetEquipClass().isInstance(e)) {
            throw new ServiceException("given skills is not supported by the service");
        }
        return saveOrUpdate((T) e);
    }

    T getByUnitId(long id);
}

package org.gassangaming.service.unit.skills;

import org.gassangaming.model.skills.UnitSkills;
import org.gassangaming.model.unit.UnitType;
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
    Class<T> getTargetSkillsClass();

    /**
     * @return concrete unit type to which unit skill refer to
     */
    UnitType getTargetUnitType();

    @Transactional
    T upgrade(long eqId, String paramNameToUpgrade, long userId) throws ServiceException;

    @Transactional
    T saveOrUpdate(T e);

    @Transactional
    default T saveOrUpdateEquip(UnitSkills e) throws ServiceException {
        if (!getTargetSkillsClass().isInstance(e)) {
            throw new ServiceException("given skills is not supported by the service");
        }
        return saveOrUpdate((T) e);
    }

    T getByUnitId(long id);
}

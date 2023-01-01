package org.gassangaming.dto.equip;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.dto.DtoBase;
import org.gassangaming.model.unit.UnitType;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpgradeUnitEquipmentRequestDto extends DtoBase {
    public long equipmentId;
    public UnitType unitType;
    public String paramNameToUpgrade;
}

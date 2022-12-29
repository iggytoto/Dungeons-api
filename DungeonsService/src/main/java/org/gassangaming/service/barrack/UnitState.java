package org.gassangaming.service.barrack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.gassangaming.model.euqipment.UnitEquip;
import org.gassangaming.model.unit.Unit;

@Data
@Value
@AllArgsConstructor
public class UnitState {

    Unit unit;
    UnitEquip equip;
}

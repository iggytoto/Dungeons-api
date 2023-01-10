package org.gassangaming.service.barrack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.gassangaming.model.item.Item;
import org.gassangaming.model.skills.UnitSkills;
import org.gassangaming.model.unit.Unit;

import java.util.Collection;

@Data
@Value
@AllArgsConstructor
public class UnitState {

    Unit unit;
    UnitSkills skills;
    Collection<Item> items;
}

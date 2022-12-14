package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gassangaming.model.unit.BattleBehavior;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeUnitBattleBehaviorRequestDto extends DtoBase {
    long unitId;
    BattleBehavior newBattleBehavior;
}

package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gassangaming.dto.unit.UnitDto;
import org.gassangaming.model.MatchType;

import java.util.ArrayList;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SaveTrainingResultRequestDto extends DtoBase {
    long userOneId;
    long userTwoId;
    long winnerUserId;
    MatchType matchType;
    Date date;
    ArrayList<UnitDto> unitsState;
}

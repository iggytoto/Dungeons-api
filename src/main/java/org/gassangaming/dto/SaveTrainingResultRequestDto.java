package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gassangaming.model.MatchType;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SaveTrainingResultRequestDto extends DtoBase {
    long userOneId;
    long userTwoId;
    long winnerId;
    MatchType matchType;
    Date date;
}

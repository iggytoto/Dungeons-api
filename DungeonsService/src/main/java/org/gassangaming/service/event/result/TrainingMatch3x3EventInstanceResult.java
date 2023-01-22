package org.gassangaming.service.event.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingMatch3x3EventInstanceResult extends EventInstanceResult {
    private long winnerId;
    private long userOneId;
    private long userTwoId;
    private Date date;
}

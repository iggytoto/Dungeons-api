package org.gassangaming.service.event.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DungeonEncounterEventInstanceResult extends EventInstanceResult {
    private long dungeonEncounterEventId;
    private List<Long> encounteredExpeditions = new ArrayList<>();
}

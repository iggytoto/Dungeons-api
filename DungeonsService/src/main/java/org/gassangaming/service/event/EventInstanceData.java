package org.gassangaming.service.event;

import lombok.Getter;
import org.gassangaming.model.unit.Unit;

import java.util.Collection;

@Getter
public class EventInstanceData {
    private Collection<Unit> eventParticipants;
}

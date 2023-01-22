package org.gassangaming.service.event.resultprocess;

import org.gassangaming.model.MatchResult;
import org.gassangaming.model.MatchType;
import org.gassangaming.model.event.EventType;
import org.gassangaming.repository.MatchResultRepository;
import org.gassangaming.service.event.result.EventInstanceResult;
import org.gassangaming.service.event.result.TrainingMatch3x3EventInstanceResult;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingMatch3x3ResultProcessStrategy implements EventInstanceResultProcessStrategy {

    @Autowired
    MatchResultRepository matchResultRepository;


    @Override
    public EventType getEventType() {
        return EventType.TrainingMatch3x3;
    }

    @Override
    public void process(EventInstanceResult eir) throws ServiceException {
        if (!(eir instanceof final TrainingMatch3x3EventInstanceResult result)) {
            return;
        }
        matchResultRepository.save(MatchResult.Of(result.getUserOneId(), result.getUserTwoId(), result.getWinnerId(), MatchType.TrainingMatch3x3, result.getDate()));
    }
}

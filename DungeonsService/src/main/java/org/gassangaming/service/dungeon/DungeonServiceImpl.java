package org.gassangaming.service.dungeon;

import org.gassangaming.model.dungeon.*;
import org.gassangaming.model.unit.Activity;
import org.gassangaming.repository.dungeon.*;
import org.gassangaming.repository.item.ItemRepository;
import org.gassangaming.repository.unit.UnitRepository;
import org.gassangaming.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class DungeonServiceImpl implements DungeonService {

    @Autowired
    DungeonInstanceRepository dungeonInstanceRepository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    DungeonExpeditionUnitsRepository dungeonExpeditionUnitsRepository;
    @Autowired
    DungeonExpeditionRepository dungeonExpeditionRepository;
    @Autowired
    DungeonInstanceExpeditionLocationRepository dungeonInstanceExpeditionLocationRepository;
    @Autowired
    DungeonRoomRepository dungeonRoomRepository;
    @Autowired
    DungeonExpeditionItemRepository dungeonExpeditionItemRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    DungeonPathRepository dungeonPathRepository;

    @Override
    public Collection<DungeonInstance> getAllActiveDungeons() {
        return dungeonInstanceRepository.findAll();
    }

    @Override
    public DungeonExpedition createExpedition(Collection<Long> roster, long startingRoomId, long dungeonInstanceId, long userId) throws ServiceException {
        final var units = unitRepository.findAllById(roster);
        if (units.stream().anyMatch(u -> u.getActivity() != Activity.Idle)) {
            throw new ServiceException("All units from roster should have idle state");
        } else if (units.stream().anyMatch(u -> u.getOwnerId() != userId)) {
            throw new ServiceException("All unit should belong to user");
        }
        final var dungeonInstance = dungeonInstanceRepository.findById(dungeonInstanceId).orElseThrow(() -> new ServiceException("Failed to find dungeon"));
        dungeonInstance.getRooms().stream().filter(r -> r.getId() == startingRoomId).findFirst().orElseThrow(() -> new ServiceException("Failed to find starting room"));
        final var expedition = dungeonExpeditionRepository.save(new DungeonExpedition(dungeonInstanceId, userId));
        units.forEach(u -> dungeonExpeditionUnitsRepository.save(new DungeonExpeditionUnit(expedition.getId(), u.getId())));
        dungeonInstanceExpeditionLocationRepository.save(new DungeonInstanceExpeditionLocation(expedition, dungeonInstanceId, startingRoomId, true));
        return expedition;
    }

    @Override
    public void returnExpeditionToTown(long expeditionId, long userId) throws ServiceException {
        final var expedition = findExpeditionById(expeditionId);
        if (expedition.getUserId() != userId) {
            throw new ServiceException("Cannot command not your expedition");
        }
        final var currentLocation = findLocationByExpeditionId(expeditionId);
        if (!currentLocation.isRoom()) {
            throw new ServiceException("Cannot return expedition from path");
        } else {
            final var room = findRoomById(currentLocation.getLocationId());
            if (room.isEntrance()) {
                final var expeditionLoot = dungeonExpeditionItemRepository.findAllByExpeditionId(expeditionId);
                itemRepository.setOwnerByIds(expeditionLoot.stream().map(DungeonExpeditionItem::getItemId).toList(), userId);
                dungeonExpeditionItemRepository.deleteAll(expeditionLoot);
                final var expeditionRoster = dungeonExpeditionUnitsRepository.findAllByExpeditionId(expeditionId);
                for (var rosterUnit : expeditionRoster) {
                    unitRepository.updateActivityByUnitId(rosterUnit.getUnitId(), Activity.Idle);
                }
                dungeonExpeditionRepository.delete(expedition);
            } else {
                throw new ServiceException("Cannot return expedition from not entrance room of dungeon");
            }
        }

    }

    @Override
    public void moveExpeditionToRoom(long targetPathId, long expeditionId, long userId) throws ServiceException {
        final var location = findLocationByExpeditionId(expeditionId);
        if (!location.isRoom()) {
            throw new ServiceException("Already moving");
        }
        final var room = findRoomById(location.getLocationId());
        final var path = findPathById(targetPathId);
        if (path.getFromRoomId() != room.getId()) {
            throw new ServiceException("Path should start from current location room");
        }
        location.setRoom(false);
        location.setLocationId(path.getId());
        location.setLocationEnteredTimestamp(new Date());
        dungeonInstanceExpeditionLocationRepository.save(location);
    }

    private DungeonPath findPathById(long id) throws ServiceException {
        return dungeonPathRepository.findById(id).orElseThrow(() -> new ServiceException("Failed to find path"));
    }

    private DungeonRoom findRoomById(long locationId) throws ServiceException {
        return dungeonRoomRepository.findById(locationId).orElseThrow(() -> new ServiceException("Failed to find room"));
    }

    private DungeonInstanceExpeditionLocation findLocationByExpeditionId(long expeditionId) throws ServiceException {
        return dungeonInstanceExpeditionLocationRepository.findById(expeditionId).orElseThrow(() -> new ServiceException("Failed to find location"));
    }

    private DungeonExpedition findExpeditionById(long expeditionId) throws ServiceException {
        return dungeonExpeditionRepository.findById(expeditionId).orElseThrow(() -> new ServiceException("Failed to find expedition"));
    }


}

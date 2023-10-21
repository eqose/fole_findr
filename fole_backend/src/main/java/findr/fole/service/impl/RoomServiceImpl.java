package findr.fole.service.impl;

import findr.fole.dto.RoomDTO;
import findr.fole.mapper.RoomMapper;
import findr.fole.repository.RoomRepository;
import findr.fole.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> findAllByFloorId(Integer id) {
        return roomRepository.findAllByBuildingFloorId(id)
                .stream()
                .map(RoomMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}

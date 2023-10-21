package findr.fole.service;

import findr.fole.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAllByFloorId(Integer id);
}

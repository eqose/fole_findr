package findr.fole.service;

import findr.fole.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<RoomDTO> findAllByFloorId(Integer id);

    List<RoomDTO> findAllFloorsBetweenDates(LocalDate dtStart, LocalDate dtEnd, Integer idFloor);
}

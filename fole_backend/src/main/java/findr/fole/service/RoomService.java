package findr.fole.service;

import findr.fole.dto.RoomDTO;
import findr.fole.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<RoomDTO> findAllByFloorId(Integer id);

    Room findByStudentId(Integer studentId);

    List<RoomDTO> findAllFloorsBetweenDates(LocalDate start, LocalDate end,  Integer idFloor);
}

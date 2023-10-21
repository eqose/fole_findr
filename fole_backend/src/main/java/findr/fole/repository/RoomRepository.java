package findr.fole.repository;

import findr.fole.model.Building;
import findr.fole.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findRoomsIdByBuildingFloorIdIn(List<Integer> id);
    List<Room> findAllByBuildingFloorId(Integer id);
}

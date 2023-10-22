package findr.fole.repository;

import findr.fole.model.Building;
import findr.fole.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findRoomsIdByBuildingFloorIdIn(List<Integer> id);
    List<Room> findAllByBuildingFloorId(Integer id);

    @Query("SELECT r FROM Room r " +
            "LEFT JOIN Contract c " +
            "ON r.id = c.room.id " +
            "AND (c.startDate > :dtEnd OR c.endDate < :dtStart) " +
            "WHERE c.id IS NULL and r.buildingFloor.id= :idParam")
    List<Room> findAvailableRoomsBetweenDates(
            @Param("dtStart") LocalDate dtStart,
            @Param("dtEnd") LocalDate dtEnd,
            @Param("idParam") Integer idFloor);
}


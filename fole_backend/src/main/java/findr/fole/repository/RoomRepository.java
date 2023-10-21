package findr.fole.repository;

import findr.fole.model.Building;
import findr.fole.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}

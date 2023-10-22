package findr.fole.repository;

import findr.fole.model.Contract;
import findr.fole.model.Room;
import findr.fole.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("SELECT DISTINCT c.students  FROM Contract c WHERE c.room.id IN :roomIds AND ((c.startDate <= :start and c.endDate >= :end) or (c.startDate >= :start and c.endDate <= :end) or (c.startDate >= :start and c.endDate >= :end))")
    List<Student> findStudentsByRoomIdIn(List<Integer> roomIds, LocalDate start, LocalDate end);
    @Query("SELECT DISTINCT c.students FROM Contract c WHERE c.id IN :id")
    List<Student> findAllDistinctStudentsById(Integer id);
    List<Contract> findAllByStudentsId(Integer studentsId);
    List<Contract> findByRoomIdIn(List<Integer> roomIds);
//    @Query("SELECT c FROM Contract c inner join Room r on c.room_id=r.id inner join BuildingFloor bf on r.building_floor_id=bf.id inner join building b on bf.building_id=b.id where end_date <= :end and start_date >= :start" +
//            " And :column = :id")
//    List<Contract> findContracts(LocalDate start, LocalDate end, String column, Integer id);

    @Query("SELECT c FROM Contract c where c.startDate <= :S and c.endDate >= :E AND c.room=:room")
    List<Contract> findContractByDateAndRoomId(LocalDate S, LocalDate E, Room room);

    @Query("SELECT c FROM Contract c where c.startDate <= :now and c.endDate >= :now ")
    List<Contract> findAllActiveByDate(LocalDate now);

}

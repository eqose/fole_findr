package findr.fole.repository;

import findr.fole.model.Contract;
import findr.fole.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("SELECT DISTINCT c.students FROM Contract c WHERE c.room.id IN :roomIds")
    List<Student> findStudentsByRoomIdIn(List<Integer> roomIds);
    @Query("SELECT DISTINCT c.students FROM Contract c WHERE c.id IN :id")
    List<Student> findAllDistinctStudentsById(Integer id);
    List<Contract> findAllByStudentsId(Integer studentsId);
    List<Contract> findByRoomIdIn(List<Integer> roomIds);
}

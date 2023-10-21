package findr.fole.repository;

import findr.fole.model.Contract;
import findr.fole.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Student> findAllDistinctStudentByRoomIdIn(List<Integer> ids);
    List<Student> findAllDistinctStudentsById(Integer id);
}

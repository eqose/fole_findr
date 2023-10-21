package findr.fole.repository;

import findr.fole.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
}

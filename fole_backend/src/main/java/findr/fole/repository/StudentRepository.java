package findr.fole.repository;

import findr.fole.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
    boolean existsStudentByNationalNo(String nationalNo);

    @Query("SELECT s FROM Student s WHERE CONCAT(s.firstName, ' ', s.lastName) LIKE %:searchTerm% OR s.firstName LIKE %:searchTerm% OR s.lastName LIKE %:searchTerm%")
    List<Student> findStudentsByFullNameContaining(String searchTerm);
}

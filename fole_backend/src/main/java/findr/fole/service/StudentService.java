package findr.fole.service;

import findr.fole.dto.StudentDTO;
import findr.fole.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll(Specification<Student> spec, Pageable pageable);
    List<StudentDTO> findAll(Specification<Student> spec);
    List<StudentDTO> findAll();
}

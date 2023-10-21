package findr.fole.service.impl;

import findr.fole.dto.StudentDTO;
import findr.fole.mapper.StudentMapper;
import findr.fole.model.Student;
import findr.fole.repository.StudentRepository;
import findr.fole.service.StudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<StudentDTO> findAll(Specification<Student> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<StudentDTO> findAll(Specification<Student> spec) {
        return null;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}

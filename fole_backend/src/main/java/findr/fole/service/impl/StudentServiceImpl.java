package findr.fole.service.impl;

import findr.fole.dto.StudentDTO;
import findr.fole.exception.DuplicateResourceException;
import findr.fole.exception.RequestValidationException;
import findr.fole.exception.ResourceNotFoundException;
import findr.fole.mapper.StudentMapper;
import findr.fole.model.Student;
import findr.fole.repository.StudentRepository;
import findr.fole.rest.req.StudentRegistrationRequest;
import findr.fole.rest.req.StudentUpdateRequest;
import findr.fole.service.StudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public StudentDTO getStudent(Integer studentId) {
        return studentRepository.findById(studentId)
                .map(StudentMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "student with id [%s] not found".formatted(studentId)
                ));
    }

    @Override
    public void addStudent(StudentRegistrationRequest request) {
        String nationalId = request.nId();
        if(studentRepository.existsCustomerWithnationalNo(nationalId)) {
            throw new DuplicateResourceException("National ID is already used");
        }

        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setNationalNo(request.nId());
        student.setGender(request.gender());
        student.setBirthDay(request.birthDay());

        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        if(!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "student with id [%s] not found".formatted(studentId)
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    public void updateStudent(Integer studentId, StudentUpdateRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "student with id [%s] not found".formatted(studentId)
        ));
        boolean changes = false;
        // todo check every case ...
        if(request.firstName() != null && !request.firstName().equals(student.getFirstName())) {
            student.setFirstName(request.firstName());
            changes = true;
        }

        if(!changes) {
            throw new RequestValidationException("No changes");
        }
        studentRepository.save(student);
    }

    @Override
    public void uploadStudentProfileImage(Integer studentId, MultipartFile file) {
        // todo
    }

    @Override
    public byte[] getStudentProfileImage(Integer studentId) {
        //todo
        return new byte[0];
    }


}

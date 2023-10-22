package findr.fole.service;

import findr.fole.dto.StudentDTO;
import findr.fole.model.Student;
import findr.fole.rest.req.StudentRegistrationRequest;
import findr.fole.rest.req.StudentUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll(Specification<Student> spec, Pageable pageable);
    List<StudentDTO> findAll(Specification<Student> spec);
    List<StudentDTO> findAll();
    StudentDTO getStudent(Integer studentId);
    void addStudent(StudentRegistrationRequest request);
    void deleteStudentById(Integer studentId);
    void updateStudent(Integer studentId, StudentUpdateRequest request);
    void uploadStudentProfileImage(Integer studentId,
                                    MultipartFile file);
    byte[] getStudentProfileImage(Integer studentID);
    List<StudentDTO> findAllByBuildingId(Integer buildingID, LocalDate start, LocalDate end);
    List<StudentDTO> findAllByBuildingFloorId(Integer id, LocalDate start, LocalDate end);
    List<StudentDTO> findAllByRoomId(Integer id, LocalDate start, LocalDate end);
    List<StudentDTO> findAllByContractId(Integer id, LocalDate start, LocalDate end);
    List<StudentDTO> findAllWithNoContract();
    List<StudentDTO> search(String searchTerm);

}

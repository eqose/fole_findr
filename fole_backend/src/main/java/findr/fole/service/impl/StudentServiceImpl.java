package findr.fole.service.impl;

import findr.fole.dto.StudentDTO;
import findr.fole.exception.DuplicateResourceException;
import findr.fole.exception.RequestValidationException;
import findr.fole.exception.ResourceNotFoundException;
import findr.fole.mapper.StudentMapper;
import findr.fole.model.Student;
import findr.fole.repository.*;
import findr.fole.rest.req.StudentRegistrationRequest;
import findr.fole.rest.req.StudentUpdateRequest;
import findr.fole.service.StudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final BuildingRepository buildingRepository;
    private final BuildingFloorRepository buildingFloorRepository;
    private final ContractRepository contractRepository;
    private final RoomRepository roomRepository;

    public StudentServiceImpl(StudentRepository studentRepository, BuildingRepository buildingRepository, BuildingFloorRepository buildingFloorRepository, ContractRepository contractRepository, RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.buildingRepository = buildingRepository;
        this.buildingFloorRepository = buildingFloorRepository;
        this.contractRepository = contractRepository;
        this.roomRepository = roomRepository;
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
        if (studentRepository.existsStudentByNationalNo(nationalId)) {
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
        if (!studentRepository.existsById(studentId)) {
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
        if (request.firstName() != null && !request.firstName().equals(student.getFirstName())) {
            student.setFirstName(request.firstName());
            changes = true;
        }

        if (!changes) {
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


    @Override
    public List<StudentDTO> findAllByBuildingId(Integer id, LocalDate start, LocalDate end) {
        List<Integer> builidngsId = buildingFloorRepository.findBuildingFloorByBuilding_Id(id).stream().map(i -> i.getId()).collect(Collectors.toList());
        List<Integer> allIdByBuildingFloorIds = roomRepository.findRoomsIdByBuildingFloorIdIn(builidngsId).stream().map(i -> i.getId()).collect(Collectors.toList());
        return contractRepository.findStudentsByRoomIdIn(allIdByBuildingFloorIds, start, end)
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllByBuildingFloorId(Integer id, LocalDate start, LocalDate end) {
        List<Integer> allIdByBuildingFloorIds = roomRepository.findRoomsIdByBuildingFloorIdIn(List.of(id)).stream().map(i -> i.getId()).collect(Collectors.toList());
        return contractRepository.findStudentsByRoomIdIn(allIdByBuildingFloorIds, start, end)
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllByRoomId(Integer id, LocalDate start, LocalDate end) {
        return contractRepository.findStudentsByRoomIdIn(List.of(id), start, end)
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllByContractId(Integer id, LocalDate start, LocalDate end) {
        return contractRepository.findAllDistinctStudentsById(id)
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllWithNoContract() {
        return this.studentRepository.findAllWithNoContract()
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> search(String searchTerm) {
        return studentRepository.findStudentsByFullNameContaining(searchTerm)
                .stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


}

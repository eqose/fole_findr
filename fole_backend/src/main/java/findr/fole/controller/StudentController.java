package findr.fole.controller;

import findr.fole.dto.StudentDTO;
import findr.fole.rest.req.StudentFilterRequest;
import findr.fole.rest.req.StudentRegistrationRequest;
import findr.fole.rest.req.StudentUpdateRequest;
import findr.fole.service.BuildingFloorService;
import findr.fole.service.BuildingService;
import findr.fole.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    private final BuildingService buildingService;
    private final BuildingFloorService buildingFloorService;
    public StudentController(StudentService studentService, BuildingService buildingService, BuildingFloorService buildingFloorService) {
        this.studentService = studentService;
        this.buildingService = buildingService;
        this.buildingFloorService = buildingFloorService;
    }

    @PostMapping("/students")
    public List<StudentDTO> getStudents(@RequestBody StudentFilterRequest request) {
        if(request.godinaId()!=null) {
            return studentService.findAllByBuildingId(request.godinaId());
        }
        if(request.katiId()!=null) {
            return studentService.findAllByBuildingFloorId(request.katiId());
        }
        if(request.roomId()!=null) {
            return studentService.findAllByRoomId(request.roomId());
        }
        if(request.contractId()!=null) {
            return studentService.findAllByContractId(request.contractId());
        }
        return studentService.findAll();
    }
    @GetMapping("/search")
    public List<StudentDTO> searchStudents(@RequestParam String searchFilter) {

        return studentService.search(searchFilter);
    }

    @GetMapping("{studentId}")
    public StudentDTO getStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRegistrationRequest request) {
        studentService.addStudent(request);
        return ResponseEntity.ok("Succesfully added!");
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("{studentId}") Integer studentId) {
        studentService.deleteStudentById(studentId);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("{studentId}") Integer studentId,
                              @RequestBody StudentUpdateRequest request) {
        studentService.updateStudent(studentId, request);
    }

    @PostMapping(
            value = "{studentId}/profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadProfileImage(
            @PathVariable("studentId") Integer customerId,
            @RequestParam("file") MultipartFile file) {
        studentService.uploadStudentProfileImage(customerId, file);
    }

    @GetMapping(
            value = "{customerId}/profile-image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getStudentProfileImage(
            @PathVariable("studentId") Integer studentId,
            @RequestParam("file") MultipartFile file) {
        // todo
        return studentService.getStudentProfileImage(studentId);
    }

}

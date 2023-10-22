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

import java.time.LocalDate;
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
        LocalDate start = request.start()==null?LocalDate.MIN:request.start();
        LocalDate end = request.end()==null?LocalDate.MAX:request.end();


        if(request.godinaId()!=null) {
            List<StudentDTO> list = studentService.findAllByBuildingId(request.godinaId(), start, end);
            for (StudentDTO studentDTO : list) {
                studentDTO.setBuilding_id(request.godinaId());
            }
            return list;
        }
        if(request.katiId()!=null) {
            List<StudentDTO> list = studentService.findAllByBuildingFloorId(request.katiId(), start, end);
            for (StudentDTO studentDTO : list) {
                studentDTO.setFloor_id(request.katiId());
            }
            return list;
        }
        if(request.roomId()!=null) {
            List<StudentDTO> list = studentService.findAllByRoomId(request.roomId(), start, end);
            for (StudentDTO studentDTO : list) {
                studentDTO.setRoom_id(request.roomId());
            }
            return list;
        }

        if(request.contractId()!=null) {
            List<StudentDTO> list = studentService.findAllByContractId(request.contractId(), start, end);
            for (StudentDTO studentDTO : list) {
                studentDTO.setContract_id(request.contractId());
                studentDTO.setStartDate(request.start());
                studentDTO.setEndDate(request.end());
            }
            return list;
        }

        if (!request.flNoContract()) {
            List<StudentDTO> list = studentService.findAllWithNoContract();
            return list;
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
    public void registerStudent(
            @RequestBody StudentRegistrationRequest request) {
        studentService.addStudent(request);
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

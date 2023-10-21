package findr.fole.controller;

import findr.fole.dto.ContractDTO;
import findr.fole.rest.req.ContractFilterRequest;
import findr.fole.rest.req.ContractInsertRequest;
import findr.fole.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("{studentId}") //todo change format
    public List<ContractDTO> getContractsByStudentID(@PathVariable("studentId") Integer studentId) {
        return contractService.findAllByStudentId(studentId);
    }

    @GetMapping()
    public List<ContractDTO> getContracts(@RequestBody ContractFilterRequest request) {
        LocalDate start = request.start()==null?LocalDate.MIN:request.start();
        LocalDate end = request.end()==null?LocalDate.MAX:request.end();
        if(request.godinaId()!=null) {
            return contractService.findAllContracts(start, end, "building_id", request.godinaId());
        }
        if(request.katiId()!=null) {
            return contractService.findAllContracts(start, end, "building_floor_id", request.godinaId());
        }
        if(request.roomId()!=null) {
            return contractService.findAllContracts(start, end, "room_id", request.godinaId());
        }
        if(request.contractId()!=null) {
            return contractService.findAllContracts(start, end, "id", request.godinaId());
        }

        return contractService.findAll();
    }

    @PostMapping
    public ResponseEntity registerContract(
            @RequestBody ContractInsertRequest request
            ) {
        contractService.addContract(request);
        return ResponseEntity.ok("Success");
    }

}

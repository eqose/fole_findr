package findr.fole.controller;

import findr.fole.dto.ContractDTO;
import findr.fole.service.ContractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("{studentId}")
    public List<ContractDTO> getContractsByStudentID(@PathVariable("studentId") Integer studentId) {
        return contractService.findAllByStudentId(studentId);
    }
}

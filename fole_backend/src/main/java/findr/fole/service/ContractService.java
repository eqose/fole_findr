package findr.fole.service;

import findr.fole.dto.ContractDTO;
import findr.fole.rest.req.ContractInsertRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<ContractDTO> findAllByStudentId(Integer studentId);
    ContractDTO find(Integer contractID);
    List<ContractDTO> findAllContracts(LocalDate start, LocalDate end, String column, Integer id);
    List<ContractDTO> findAll();
    void addContract(ContractInsertRequest request);

}

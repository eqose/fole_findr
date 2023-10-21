package findr.fole.service;

import findr.fole.dto.ContractDTO;

import java.util.List;

public interface ContractService {
    List<ContractDTO> findAllByStudentId(Integer studentId);
}

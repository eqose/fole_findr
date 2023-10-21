package findr.fole.service.impl;

import findr.fole.dto.ContractDTO;
import findr.fole.mapper.ContractMapper;
import findr.fole.repository.ContractRepository;
import findr.fole.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public List<ContractDTO> findAllByStudentId(Integer studentId) {
        return contractRepository.findAllByStudentsId(studentId)
                .stream()
                .map(ContractMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}

package findr.fole.service.impl;

import findr.fole.dto.ContractDTO;
import findr.fole.exception.RequestValidationException;
import findr.fole.exception.ResourceNotFoundException;
import findr.fole.mapper.ContractMapper;
import findr.fole.model.Contract;
import findr.fole.model.Room;
import findr.fole.model.Student;
import findr.fole.repository.ContractRepository;
import findr.fole.repository.RoomRepository;
import findr.fole.repository.StudentRepository;
import findr.fole.rest.req.ContractInsertRequest;
import findr.fole.service.ContractService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    public ContractServiceImpl(ContractRepository contractRepository, StudentRepository studentRepository, RoomRepository roomRepository) {
        this.contractRepository = contractRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public List<ContractDTO> findAllByStudentId(Integer studentId) {
        return contractRepository.findAllByStudentsId(studentId)
                .stream()
                .map(ContractMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO find(Integer contractID) {
        return ContractMapper
                .INSTANCE
                .toDTO(contractRepository.findById(contractID).get());
    }

    @Override
    public List<ContractDTO> findAllContracts(LocalDate start, LocalDate end, String column, Integer id) {
//        return contractRepository.findContracts(start, end, column, id)
//                .stream()
//                .map(ContractMapper.INSTANCE::toDTO)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<ContractDTO> findAll() {
        return contractRepository.findAll()
                .stream()
                .map(ContractMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContractDTO> findAllByStatus(boolean flag) {
        LocalDate now = LocalDate.now();
        return contractRepository.findAllActiveByDate(now)
                .stream()
                .map(ContractMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void addContract(ContractInsertRequest request) {
        Room room = roomRepository.findById(request.roomId()).orElseThrow(
                () -> new ResourceNotFoundException("Room not found")
        );

        List<Contract> contracts = contractRepository.findContractByDateAndRoomId(request.start(), request.end(), room);
//                .stream()
//                .filter(i -> i.getRoom().equals(room))
//                .collect(Collectors.toList());

        if(contracts.size()>0) {
            throw new RequestValidationException("There is already an contract.");
        }

        Student student = studentRepository.findById(request.studentId()).orElseThrow(
                () -> new ResourceNotFoundException("Student not found")
        );

        Contract contract = new Contract();
        contract.setStudents(student);
        contract.setRoom(room);
        contract.setStartDate(request.start());
        contract.setEndDate(request.end());
        contract.setNote(request.note());

        contractRepository.save(contract);

    }
}

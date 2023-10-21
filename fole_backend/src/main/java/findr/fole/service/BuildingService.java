package findr.fole.service;

import findr.fole.dto.BuildingDTO;
import findr.fole.dto.BuildingFloorDTO;
import findr.fole.model.BuildingFloor;
import findr.fole.rest.req.StudentRegistrationRequest;
import findr.fole.rest.req.StudentUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> findAll(Specification<BuildingFloor> spec, Pageable pageable);
    List<BuildingDTO> findAll(Specification<BuildingFloor> spec);
    List<BuildingDTO> findAll();
}

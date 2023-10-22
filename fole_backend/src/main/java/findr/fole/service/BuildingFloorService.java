package findr.fole.service;

import findr.fole.dto.BuildingFloorDTO;
import findr.fole.dto.RoomDTO;
import findr.fole.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BuildingFloorService {
    List<BuildingFloorDTO> findAll(Specification<BuildingFloorDTO> spec, Pageable pageable);
    List<BuildingFloorDTO> findAll(Specification<BuildingFloorDTO> spec);
    List<BuildingFloorDTO> findAll();
    List<BuildingFloorDTO> findAll(Integer buildingId);
}

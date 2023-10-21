package findr.fole.service.impl;

import findr.fole.dto.BuildingFloorDTO;
import findr.fole.mapper.BuildingFloorMapper;
import findr.fole.model.BuildingFloor;
import findr.fole.repository.BuildingFloorRepository;
import findr.fole.repository.BuildingRepository;
import findr.fole.service.BuildingService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingFloorRepository buildingFloorRepository;

    public BuildingServiceImpl(BuildingFloorRepository buildingFloorRepository) {
        this.buildingFloorRepository = buildingFloorRepository;
    }


    @Override
    public List<BuildingFloorDTO> findAll(Specification<BuildingFloor> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<BuildingFloorDTO> findAll(Specification<BuildingFloor> spec) {
        return null;
    }

    @Override
    public List<BuildingFloorDTO> findAll() {
        return buildingFloorRepository.findAll()
                .stream()
                .map(BuildingFloorMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


}

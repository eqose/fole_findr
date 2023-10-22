package findr.fole.service.impl;

import findr.fole.dto.BuildingDTO;
import findr.fole.dto.BuildingFloorDTO;
import findr.fole.mapper.BuildingFloorMapper;
import findr.fole.mapper.BuildingMapper;
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

    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }


    @Override
    public List<BuildingDTO> findAll(Specification<BuildingFloor> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<BuildingDTO> findAll(Specification<BuildingFloor> spec) {
        return null;
    }

    @Override
    public List<BuildingDTO> findAll() {
        return buildingRepository.findAll()
                .stream()
                .map(BuildingMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

}

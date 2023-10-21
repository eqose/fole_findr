package findr.fole.service.impl;

import findr.fole.dto.BuildingFloorDTO;
import findr.fole.mapper.BuildingFloorMapper;
import findr.fole.repository.BuildingFloorRepository;
import findr.fole.service.BuildingFloorService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingFloorServiceImpl implements BuildingFloorService {
    private final BuildingFloorRepository buildingFloorRepository;

    public BuildingFloorServiceImpl(BuildingFloorRepository buildingFloorRepository) {
        this.buildingFloorRepository = buildingFloorRepository;
    }

    @Override
    public List<BuildingFloorDTO> findAll(Specification<BuildingFloorDTO> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<BuildingFloorDTO> findAll(Specification<BuildingFloorDTO> spec) {
        return buildingFloorRepository.findAll((Sort) spec)
                .stream()
                .map(BuildingFloorMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BuildingFloorDTO> findAll() {
        return buildingFloorRepository.findAll()
                .stream()
                .map(BuildingFloorMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BuildingFloorDTO> findAll(Integer buildingId) {
        return buildingFloorRepository.findBuildingFloorByBuilding_Id(buildingId)
                .stream()
                .map(BuildingFloorMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}

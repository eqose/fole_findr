package findr.fole.mapper;

import findr.fole.dto.BuildingFloorDTO;
import findr.fole.model.BuildingFloor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public  interface BuildingFloorMapper {
    BuildingFloorMapper INSTANCE = Mappers.getMapper(BuildingFloorMapper.class);

    BuildingFloor toEntity(BuildingFloorDTO dto);

    BuildingFloorDTO toDTO(BuildingFloor entity);
}

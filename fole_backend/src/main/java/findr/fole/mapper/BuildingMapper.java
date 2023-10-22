package findr.fole.mapper;

import findr.fole.dto.BuildingDTO;
import findr.fole.model.Building;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);
    Building toEntity(BuildingDTO dto);

    BuildingDTO toDTO(Building entity);
}

package findr.fole.mapper;

import findr.fole.dto.RoomDTO;
import findr.fole.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room toEntity(RoomDTO dto);

    RoomDTO toDTO(Room entity);
}

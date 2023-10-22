package findr.fole.mapper;

import findr.fole.dto.ContractDTO;
import findr.fole.dto.RoomDTO;
import findr.fole.model.Contract;
import findr.fole.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    Contract toEntity(ContractDTO dto);

    ContractDTO toDTO(Contract entity);
}

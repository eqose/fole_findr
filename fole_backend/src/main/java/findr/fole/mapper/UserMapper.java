package findr.fole.mapper;

import findr.fole.dto.StudentDTO;
import findr.fole.dto.UserDTO;
import findr.fole.model.Student;
import findr.fole.model.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);
}

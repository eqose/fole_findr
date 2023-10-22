package findr.fole.mapper;

import findr.fole.dto.StudentDTO;
import findr.fole.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDTO dto);

    StudentDTO toDTO(Student entity);
}

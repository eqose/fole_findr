package findr.fole.dto;

import findr.fole.model.Room;
import findr.fole.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {
    private Integer id;
    private RoomDTO room;
    private StudentDTO students;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;
}

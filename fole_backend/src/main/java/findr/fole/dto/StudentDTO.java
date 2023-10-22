package findr.fole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String nationalNo;
    private int floor_id;
    private int room_id;
    private int building_id;
    private int contract_id;
    private LocalDate startDate;
    private LocalDate endDate;
}

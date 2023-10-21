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
}

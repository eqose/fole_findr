package findr.fole.rest.req;

import findr.fole.model.Student;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ContractInsertRequest(Integer roomId, Integer studentId, LocalDate start, LocalDate end, String note) {
}

package findr.fole.rest.req;

import java.time.LocalDate;

public record ContractFilterRequest(Integer godinaId, Integer katiId, Integer roomId, Integer contractId, Integer studentId ,
                                    LocalDate start, LocalDate end) {
}

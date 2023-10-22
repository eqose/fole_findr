package findr.fole.rest.req;

import java.time.LocalDate;

public record StudentFilterRequest(Integer godinaId, Integer katiId, Integer roomId, Integer contractId ,
                                   LocalDate start, LocalDate end, boolean flNoContract)  {
}

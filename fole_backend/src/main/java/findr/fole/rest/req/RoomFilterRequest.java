package findr.fole.rest.req;

import java.time.LocalDate;

public record RoomFilterRequest(LocalDate start, LocalDate end) {
}

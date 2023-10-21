package findr.fole.rest.req;

import findr.fole.enums.Gender;

import java.time.LocalDate;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String password,
        String email,
        String nId,
        LocalDate birthDay,
        Gender gender
) {
}
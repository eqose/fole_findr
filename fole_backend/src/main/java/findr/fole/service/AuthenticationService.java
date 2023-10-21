package findr.fole.service;

import findr.fole.rest.req.SignUpRequest;
import findr.fole.rest.req.SigninRequest;
import findr.fole.rest.res.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

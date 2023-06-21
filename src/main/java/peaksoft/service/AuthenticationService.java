package peaksoft.service;


import peaksoft.dto.Authentication.AuthenticationResponse;
import peaksoft.dto.Authentication.SignInRequest;

public interface AuthenticationService {

    AuthenticationResponse signIn(SignInRequest signInRequest);

}

package Authentication.example.Authentication.authenticationservice;

import Authentication.example.Authentication.dtos.AuthenticationDto;
import Authentication.example.Authentication.dtos.AuthenticationResponse;
import Authentication.example.Authentication.dtos.RegisterDto;

public interface UserService {
    AuthenticationResponse register(RegisterDto request);
    AuthenticationResponse login(AuthenticationDto request);
    AuthenticationResponse emailConfirm(String email);
    AuthenticationResponse resetpassword(AuthenticationDto request);
}

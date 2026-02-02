package Authentication.example.Authentication.authenticationservice.Impl;

import Authentication.example.Authentication.authenticationservice.UserService;
import Authentication.example.Authentication.config.JwtService;  
import Authentication.example.Authentication.dtos.AuthenticationDto;
import Authentication.example.Authentication.dtos.AuthenticationResponse;
import Authentication.example.Authentication.dtos.RegisterDto;
import Authentication.example.Authentication.dtos.UserResponse;
import Authentication.example.Authentication.entity.User;
import Authentication.example.Authentication.repository.UserRepository;       
import Authentication.example.Authentication.util.UniqueKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;    
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterDto request) {

        try {
            if (repository.findByEmail(request.getEmail()).isPresent()) {
                return AuthenticationResponse.builder()
                        .error(true)
                        .message("Email is already in use")
                        .build();
            }

            var user = User.builder()
                    .id(UniqueKey.UniKey())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .roles(request.getRoles())
                    .phoneNumber(request.getPhoneNumber())
                    .createdDate(new Timestamp(System.currentTimeMillis()))
                    .lastChangeDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            repository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .error(false)
                    .message("User registered successfully ")
                    .success(true)
                    .user(UserResponse.builder()
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .token(jwtToken)
                            .build())
                    .build();

        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .error(true)
                    .message("An unexpected error occurred: " + e.getMessage())
                    .build();
        }
    }


    @Override
    public AuthenticationResponse login(AuthenticationDto request) {
        try {
            var userOptional = repository.findByEmail(request.getEmail());
            if (userOptional.isEmpty()) {
                return AuthenticationResponse.builder()
                        .error(true)
                        .message("Invalid credentials")
                        .build();
            }

            var user = userOptional.get();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return AuthenticationResponse.builder()
                        .error(true)
                        .message("Invalid credentials")
                        .build();
            }

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .error(false)
                    .message("Login successful")
                    .success(true)
                    .user(UserResponse.builder()
                            .email(user.getEmail())
                            .role(user.getRoles())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .token(jwtToken)
                            .build())
                    .build();
        } catch (Exception e) {

            return AuthenticationResponse.builder()
                    .error(true)
                    .message("An unexpected error occurred: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public AuthenticationResponse emailConfirm(String email) {
        try{
            var userOptional = repository.findByEmail(email);
            if(userOptional.isEmpty()){
                return AuthenticationResponse.builder()
                        .error(true)
                        .message("Invalid email")
                        .build();
            }

            return AuthenticationResponse.builder()
                    .error(false)
                    .message("successful")
                    .success(true)
                    .user(UserResponse.builder()
                            .email(userOptional.get().getEmail())
                            .build())
                    .build();
        }catch (Exception e){
            return AuthenticationResponse.builder()
                    .error(true)
                    .message("An unexpected error occurred: " + e.getMessage())
                    .build();
        }

    }

    @Override
    public AuthenticationResponse resetpassword(AuthenticationDto request) {
        Optional<User> userOptional = repository.findByEmail(request.getEmail());
        if(userOptional.isEmpty()){
            return AuthenticationResponse.builder()
                    .error(true)
                    .message("Invalid email")
                    .build();
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLastChangeDate(new Timestamp(System.currentTimeMillis()));


        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .error(false)
                .message("User reset password successfully ")
                .success(true)
                .user(UserResponse.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .token(jwtToken)
                        .build())
                .build();
    }
}

package Authentication.example.Authentication.controller;

import Authentication.example.Authentication.authenticationservice.UserService;
import Authentication.example.Authentication.dtos.AuthenticationDto;
import Authentication.example.Authentication.dtos.AuthenticationResponse;
import Authentication.example.Authentication.dtos.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auths")
@RequiredArgsConstructor
public class userController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDto request
    ) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationDto request
    ) {
        return ResponseEntity.ok(userService.login(request));
    }
    @PostMapping("/confirmEmail")
    public ResponseEntity<AuthenticationResponse> confirmEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.emailConfirm(email));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<AuthenticationResponse> resetpassword(@RequestBody  AuthenticationDto request){
        return ResponseEntity.ok(userService.resetpassword(request));
    }

}

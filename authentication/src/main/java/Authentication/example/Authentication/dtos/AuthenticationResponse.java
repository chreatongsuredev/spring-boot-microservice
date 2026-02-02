package Authentication.example.Authentication.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Boolean error;
    private Boolean success = false;
    private String message;
    private UserResponse user;

}

package Authentication.example.Authentication.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String token;

}

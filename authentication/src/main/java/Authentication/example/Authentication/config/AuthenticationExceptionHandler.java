package Authentication.example.Authentication.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class AuthenticationExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message = (String) request.getAttribute("JWT_ERROR");
        if (message == null) {
            message = "Authentication Failed: " + authException.getMessage();
        } else {
            message = "Authentication Failed: " + message;
        }

        objectMapper.writeValue(response.getWriter(), message);
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getWriter(),"Access Denied: You do not have permission to access this resource.");
    }
}

package Authentication.example.Authentication.App.interceptor;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

@WebFilter("/*")
public class DecryptInterceptor implements HandlerInterceptor {

    private static final String SECRET_KEY = "MySecretKey12345"; // replace with your actual key

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {

            // Read the original request body
            String encryptedBody = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            // Decrypt the body
            String decryptedBody = decrypt(encryptedBody);


            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public BufferedReader getReader() throws IOException {
                    return new BufferedReader(new StringReader(decryptedBody));
                }

                @Override
                public ServletInputStream getInputStream() throws IOException {
                    final ByteArrayInputStream byteArrayInputStream =
                            new ByteArrayInputStream(decryptedBody.getBytes(StandardCharsets.UTF_8));

                    return new ServletInputStream() {
                        @Override
                        public int read() throws IOException {
                            return byteArrayInputStream.read();
                        }

                        @Override
                        public boolean isFinished() {
                            return byteArrayInputStream.available() == 0;
                        }

                        @Override
                        public boolean isReady() {
                            return true;
                        }

                        @Override
                        public void setReadListener(ReadListener listener) {

                        }
                    };
                }
            };

            // Replace the original request with the wrapped one
            request.setAttribute("decryptedRequest", wrappedRequest);
        }

        return true; // continue processing
    }

    private String decrypt(String encrypted) throws Exception {
        if (encrypted == null || encrypted.isEmpty()) return "";

        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}

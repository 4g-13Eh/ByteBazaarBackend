package ByteBazaar.ByteBazaarBackend.security.service;

import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    JwtTokenDto signup(SignUpDto request);
    JwtTokenDto signin(SignInDto request, HttpServletResponse response);
    void logout(String token, HttpServletResponse response);
    JwtTokenDto refreshAccessToken(HttpServletRequest request, HttpServletResponse response);
}

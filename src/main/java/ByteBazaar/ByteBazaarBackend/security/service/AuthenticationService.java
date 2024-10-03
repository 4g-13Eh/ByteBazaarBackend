package ByteBazaar.ByteBazaarBackend.security.service;

import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    JwtTokenDto signup(SignUpDto request, HttpServletResponse response);
    JwtTokenDto signin(SignInDto request, HttpServletResponse response);
    void logout(HttpServletRequest request, HttpServletResponse response);
    JwtTokenDto refreshAccessToken(HttpServletRequest request, HttpServletResponse response);
}

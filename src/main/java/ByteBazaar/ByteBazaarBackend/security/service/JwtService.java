package ByteBazaar.ByteBazaarBackend.security.service;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails, TokenType tokenType);
    boolean isTokenValid(String token);
    void saveToken(UserEntity user, String token, TokenType tokenType);
    String getTokenFromRequest(HttpServletRequest request);
    boolean isAuthenticated(HttpServletRequest request);
}

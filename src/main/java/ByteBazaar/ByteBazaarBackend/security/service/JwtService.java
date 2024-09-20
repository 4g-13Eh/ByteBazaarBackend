package ByteBazaar.ByteBazaarBackend.security.service;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails, TokenType tokenType);
    boolean isTokenValid(String token, UserDetails userDetails);
    void saveToken(UserEntity user, String token, TokenType tokenType);
    void updateRefreshToken(UserEntity user, String oldRefreshToken, String newRefreshToken);
}

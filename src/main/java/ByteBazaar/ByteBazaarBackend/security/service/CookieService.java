package ByteBazaar.ByteBazaarBackend.security.service;

import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {
    void addTokenToCookie(HttpServletResponse response, String Token, TokenType tokenType);
    String getTokenFromCookie(HttpServletRequest request, TokenType tokenType);
    void clearTokenCookie(HttpServletResponse response, TokenType tokenType);
}

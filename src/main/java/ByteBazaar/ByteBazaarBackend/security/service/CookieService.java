package ByteBazaar.ByteBazaarBackend.security.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {
    void addRefreshTokenToCookie(HttpServletResponse response, String refreshToken);
    String getRefreshTokenFromCookie(HttpServletRequest request);
    void clearRefreshTokenCookie(HttpServletResponse response);
}

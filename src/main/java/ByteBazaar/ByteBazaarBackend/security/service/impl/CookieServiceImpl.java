package ByteBazaar.ByteBazaarBackend.security.service.impl;

import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import ByteBazaar.ByteBazaarBackend.security.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {
    @Override
    public void addTokenToCookie(HttpServletResponse response, String token, TokenType tokenType) {
        String cookieName = (tokenType == TokenType.ACCESS) ? "accessToken" : "refreshToken";
        Cookie cookie = new Cookie(cookieName, token);
        cookie.setHttpOnly(true);
        if (tokenType == TokenType.REFRESH){
            cookie.setMaxAge(300); // One week
        } else if (tokenType == TokenType.ACCESS) {
            cookie.setMaxAge(180); // One day
        }
        cookie.setPath("/api");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    @Override
    public String getTokenFromCookie(HttpServletRequest request, TokenType tokenType) {
        String cookieName = (tokenType == TokenType.ACCESS) ? "accessToken" : "refreshToken";
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void clearTokenCookie(HttpServletResponse response, TokenType tokenType) {
        String cookieName = (tokenType == TokenType.ACCESS) ? "accessToken" : "refreshToken";
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/api");
        response.addCookie(cookie);
    }
}

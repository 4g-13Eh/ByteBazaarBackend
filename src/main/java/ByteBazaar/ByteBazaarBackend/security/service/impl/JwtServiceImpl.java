package ByteBazaar.ByteBazaarBackend.security.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.TokenEntity;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import ByteBazaar.ByteBazaarBackend.exception.TokenNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.TokenRepository;
import ByteBazaar.ByteBazaarBackend.security.service.JwtService;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = Dotenv.load().get("SECRET_KEY");
    @Value("${application.security.jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${application.security.jwt.refresh.expiration}")
    private long refreshTokenExpiration;
    private final TokenRepository tokenRepository;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails, TokenType tokenType){
        return generateToken(new HashMap<>(), userDetails, tokenType);
    }

    @Override
    public boolean isTokenValid(String token) {
        final String userName = extractUsername(token);
        boolean isExpired = isTokenExpired(token);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
        TokenEntity tokenEntity = tokenRepository.findByToken(token).orElseThrow(TokenNotFoundException::new);

        if (isExpired) {
            return false;
        }
        if (tokenEntity.isRevoked()){
            return false;
        }

        return (userName.equals(userDetails.getUsername()));
    }

    @Override
    public void saveToken(UserEntity user, String token, TokenType tokenType){
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);
        tokenEntity.setTokenType(tokenType);
        tokenEntity.setExpired(false);
        tokenEntity.setRevoked(false);
        tokenRepository.save(tokenEntity);
    }

    @Override
    public String getTokenFromRequest(HttpServletRequest request){
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()){
                if ("accessToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        return token != null && isTokenValid(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, TokenType tokenType) {
        long expirationTime = (tokenType == TokenType.ACCESS) ? accessTokenExpiration : refreshTokenExpiration;
        extraClaims.put("jti", UUID.randomUUID().toString());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

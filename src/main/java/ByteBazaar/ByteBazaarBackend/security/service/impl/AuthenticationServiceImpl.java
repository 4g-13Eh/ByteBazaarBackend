package ByteBazaar.ByteBazaarBackend.security.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.TokenEntity;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import ByteBazaar.ByteBazaarBackend.exception.InvalidEmailOrPasswordException;
import ByteBazaar.ByteBazaarBackend.exception.TokenNotFoundException;
import ByteBazaar.ByteBazaarBackend.exception.UserNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.TokenRepository;
import ByteBazaar.ByteBazaarBackend.repository.UserRepository;
import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import ByteBazaar.ByteBazaarBackend.security.service.AuthenticationService;
import ByteBazaar.ByteBazaarBackend.security.service.CookieService;
import ByteBazaar.ByteBazaarBackend.security.service.JwtService;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final CookieService cookieService;

    @Override
    public JwtTokenDto signup(SignUpDto request, HttpServletResponse response) {
        UserEntity user = userService.createUser(request.getEmail(), request.getPassword(), request.getConfirmedPassword());

        String accessToken = this.jwtService.generateToken(user, TokenType.ACCESS);
        String refreshToken = this.jwtService.generateToken(user, TokenType.REFRESH);

        jwtService.saveToken(user, accessToken, TokenType.ACCESS);
        jwtService.saveToken(user, refreshToken, TokenType.REFRESH);

        cookieService.addTokenToCookie(response, accessToken, TokenType.ACCESS);
        cookieService.addTokenToCookie(response, refreshToken, TokenType.REFRESH);

        return new JwtTokenDto(accessToken);
    }

    @Override
    public JwtTokenDto signin(SignInDto request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidEmailOrPasswordException::new);

        revokeAllValidTokens(user.getUserId());

        String accessToken = this.jwtService.generateToken(user, TokenType.ACCESS);
        String refreshToken = this.jwtService.generateToken(user, TokenType.REFRESH);

        jwtService.saveToken(user, accessToken, TokenType.ACCESS);
        jwtService.saveToken(user, refreshToken, TokenType.REFRESH);

        cookieService.addTokenToCookie(response, accessToken, TokenType.ACCESS);
        cookieService.addTokenToCookie(response, refreshToken, TokenType.REFRESH);

        return new JwtTokenDto(accessToken);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response){
        String accessToken = cookieService.getTokenFromCookie(request, TokenType.ACCESS);
        String refreshToken = cookieService.getTokenFromCookie(request, TokenType.REFRESH);

        TokenEntity accessTokenEntity = tokenRepository.findByToken(accessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));

        TokenEntity refreshTokenEntity = tokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));

        accessTokenEntity.setRevoked(true);
        refreshTokenEntity.setRevoked(true);
        cookieService.clearTokenCookie(response, TokenType.ACCESS);
        cookieService.clearTokenCookie(response, TokenType.REFRESH);

        tokenRepository.save(accessTokenEntity);
        tokenRepository.save(refreshTokenEntity);
    }

    @Override
    public JwtTokenDto refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = cookieService.getTokenFromCookie(request, TokenType.REFRESH);
        TokenEntity token = tokenRepository.findByToken(refreshToken).orElseThrow(TokenNotFoundException::new);

        if (refreshToken != null
                && token.getTokenType() == TokenType.REFRESH
                && refreshToken.equals(token.getToken())
        ){
            String username = jwtService.extractUsername(refreshToken);
            UserEntity user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);

            setTokensToExpired(user.getUserId());
            this.cookieService.clearTokenCookie(response, TokenType.ACCESS);

            String newAccessToken = jwtService.generateToken(user, TokenType.ACCESS);
            this.cookieService.addTokenToCookie(response, newAccessToken, TokenType.ACCESS);

            jwtService.saveToken(user, newAccessToken, TokenType.ACCESS);
            return new JwtTokenDto(newAccessToken);
        }

        return null;
    }

    private void revokeAllValidTokens(String userId) {
        List<TokenEntity> validTokens = tokenRepository.findAllValidTokensByUser(userId);
        revokeTokens(validTokens);
    }

    private void setTokensToExpired(String userId) {
        List<TokenEntity> validAccessTokens = tokenRepository.findAllValidAccessTokensByUser(userId);
        expireTokens(validAccessTokens);
    }

    private void revokeTokens(List<TokenEntity> tokens) {
        if (!tokens.isEmpty()) {
            tokens.forEach(token -> {
                token.setRevoked(true);
                tokenRepository.save(token);
            });
        }
    }

    private void expireTokens(List<TokenEntity> tokens) {
        if (!tokens.isEmpty()) {
            tokens.forEach(token -> {
                token.setExpired(true);
                tokenRepository.save(token);
            });
        }
    }

}

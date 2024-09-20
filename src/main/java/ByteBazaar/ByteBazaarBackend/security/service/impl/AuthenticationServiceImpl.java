package ByteBazaar.ByteBazaarBackend.security.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.TokenEntity;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import ByteBazaar.ByteBazaarBackend.exception.InvalidEmailOrPasswordException;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final CookieService cookieService;

    @Override
    public JwtTokenDto signup(SignUpDto request) {
        UserEntity user = userService.createUser(request.getEmail(), request.getPassword(), request.getConfirmedPassword());

        String accessToken = this.jwtService.generateToken(user, TokenType.ACCESS);
        String refreshToken = this.jwtService.generateToken(user, TokenType.REFRESH);

        jwtService.saveToken(user, accessToken, TokenType.ACCESS);
        jwtService.saveToken(user, refreshToken, TokenType.REFRESH);

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

        cookieService.addRefreshTokenToCookie(response, refreshToken);

        return new JwtTokenDto(accessToken);
    }

    @Override
    public void logout(String token, HttpServletResponse response){
        TokenEntity tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));

        tokenEntity.setRevoked(true);
        cookieService.clearRefreshTokenCookie(response);
        tokenRepository.save(tokenEntity);
    }

    @Override
    public JwtTokenDto refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = cookieService.getRefreshTokenFromCookie(request);
        Optional<TokenEntity> token = tokenRepository.findByToken(refreshToken);
        if (refreshToken != null && token.get().getTokenType() == TokenType.REFRESH){
            String username = jwtService.extractUsername(refreshToken);
            UserEntity user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
            String newAccessToken = jwtService.generateToken(user, TokenType.ACCESS);

            // Optionally refresh the refresh token (rotate) for security
            // String newRefreshToken = jwtService.generateToken(user, TokenType.REFRESH);
            // jwtService.updateRefreshToken(user, refreshToken, newRefreshToken);
            // cookieUtil.addRefreshTokenToCookie(response, newRefreshToken);

            return new JwtTokenDto(newAccessToken);
        }

        return null;
    }

    private void revokeAllValidTokens(String userId){
        List<TokenEntity> validTokens = tokenRepository.findAllValidTokensByUser(userId);
        if (!validTokens.isEmpty()) {
            validTokens.forEach(token -> {
                token.setRevoked(true);
                tokenRepository.save(token);
            });
        }
    }
}

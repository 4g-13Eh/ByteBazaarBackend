package ByteBazaar.ByteBazaarBackend.security.service;

import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;

public interface AuthenticationService {
    JwtTokenDto signup(SignUpDto request);
    JwtTokenDto signin(SignInDto request);
}

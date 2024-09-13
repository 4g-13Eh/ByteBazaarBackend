package ByteBazaar.ByteBazaarBackend.security.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.exception.InvalidEmailOrPasswordException;
import ByteBazaar.ByteBazaarBackend.repository.UserRepository;
import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import ByteBazaar.ByteBazaarBackend.security.service.AuthenticationService;
import ByteBazaar.ByteBazaarBackend.security.service.JwtService;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtTokenDto signup(SignUpDto request) {
        UserEntity user = userService.createUser(request.getEmail(), request.getPassword(), request.getConfirmedPassword());
        String jwt = jwtService.generateToken(user);
        return new JwtTokenDto(jwt);
    }

    @Override
    public JwtTokenDto signin(SignInDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidEmailOrPasswordException::new);
        String jwt = this.jwtService.generateToken(user);
        return new JwtTokenDto(jwt);
    }
}

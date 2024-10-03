package ByteBazaar.ByteBazaarBackend.security.controller;

import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import ByteBazaar.ByteBazaarBackend.security.service.AuthenticationService;
import ByteBazaar.ByteBazaarBackend.security.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<JwtTokenDto> signup(@RequestBody @Valid SignUpDto data, HttpServletResponse response){
        JwtTokenDto jwtToken = authenticationService.signup(data , response);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtTokenDto> signin(@RequestBody @Valid SignInDto data, HttpServletResponse response){
        JwtTokenDto jwtToken = authenticationService.signin(data, response);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenDto> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        JwtTokenDto newAccessToken = authenticationService.refreshAccessToken(request, response);
        return ResponseEntity.ok(newAccessToken);
    }

    @GetMapping("/is-authenticated")
    public ResponseEntity<Boolean> isAuthenticated(HttpServletRequest request) {
        return ResponseEntity.ok(jwtService.isAuthenticated(request));
    }
}

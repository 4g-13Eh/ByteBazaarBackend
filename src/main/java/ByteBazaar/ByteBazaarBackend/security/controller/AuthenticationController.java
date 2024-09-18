package ByteBazaar.ByteBazaarBackend.security.controller;

import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import ByteBazaar.ByteBazaarBackend.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtTokenDto> signup(@RequestBody @Valid SignUpDto data){
        JwtTokenDto jwtToken = authenticationService.signup(data);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtTokenDto> signin(@RequestBody @Valid SignInDto data){
        JwtTokenDto jwtToken = authenticationService.signin(data);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid Token");
        }

        String token = authHeader.substring(7);
        authenticationService.logout(token);

        return ResponseEntity.noContent().build();
    }
}

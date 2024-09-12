package ByteBazaar.ByteBazaarBackend.security.controller;

import ByteBazaar.ByteBazaarBackend.security.dto.JwtTokenDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignInDto;
import ByteBazaar.ByteBazaarBackend.security.dto.SignUpDto;
import ByteBazaar.ByteBazaarBackend.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    JwtTokenDto signup(@RequestBody SignUpDto data){
        return authenticationService.signup(data);
    }

    @PostMapping("/signin")
    JwtTokenDto signin(@RequestBody SignInDto data){
        return authenticationService.signin(data);
    }
}

package com.ByteBazaar.ByteBazaarBackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public int welcome(){
        return 2;
    }
}

package com.ByteBazaar.ByteBazaarBackend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("api/users/{id}")
    String getUserById(@PathVariable("id") String id){
        return "Hi";
    }

    @PostMapping("api/users")
    String createUser(@RequestBody String user){
        return "Created User with Id: " + user;
    }
}

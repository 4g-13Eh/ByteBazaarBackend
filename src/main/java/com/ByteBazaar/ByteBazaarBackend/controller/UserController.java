package com.ByteBazaar.ByteBazaarBackend.controller;

import com.ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import com.ByteBazaar.ByteBazaarBackend.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/users/{id}")
    UserEntity getUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @PostMapping("api/users/register")
    String createUser(@RequestBody UserEntity user){
        UserEntity createdUser = userService.createUser(user);
        return "Created User with Id: " + createdUser.getUserId();
    }


}

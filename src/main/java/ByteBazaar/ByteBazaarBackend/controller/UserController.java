package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.dto.GetUserDto;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    GetUserDto getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    UserEntity registerUser(@RequestBody UserEntity user){
        return userService.createUser(user);
    }

    @PutMapping("/{userId}")
    GetUserDto assignCartToUser(@PathVariable("userId") String userId, String cartId){
        return userService.assignCartToUser(userId, cartId);
    }
}

package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserEntity getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    UserEntity assignCartToUser(@PathVariable("userId") String userId, String cartId){
        return userService.assignCartToUser(userId, cartId);
    }
}

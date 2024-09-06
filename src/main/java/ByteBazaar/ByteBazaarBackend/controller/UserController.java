package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    UserEntity getUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @PostMapping("register")
    String createUser(@RequestBody UserEntity user){
        UserEntity createdUser = userService.createUser(user);
        return "Created User with Id: " + createdUser.getUserId();
    }

    @PutMapping("{id}")
    UserEntity upateCartOfUser(@PathVariable("id") String userId, @RequestBody UserEntity user) {
        return userService.assignCartToUser(userId, user.getCartId());
    }
}

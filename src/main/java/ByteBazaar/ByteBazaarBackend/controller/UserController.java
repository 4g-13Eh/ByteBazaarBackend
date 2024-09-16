package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.controller.dto.UserDto;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserEntity> users = userService.getAllUsers();
        if (users.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public UserEntity assignCartToUser(@PathVariable("userId") String userId, String cartId){
        return userService.assignCartToUser(userId, cartId);
    }

    private UserDto convertToDto(UserEntity user) {
        return new UserDto(user.getUserId(), user.getEmail(), user.getCartId(), user.getUsername());
    }
}

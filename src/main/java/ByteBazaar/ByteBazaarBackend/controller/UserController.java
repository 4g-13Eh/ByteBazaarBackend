package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.controller.dto.UserDto;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import ByteBazaar.ByteBazaarBackend.utils.DtoConverter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<@Valid UserDto>> getAllUsers(){
        List<UserEntity> users = userService.getAllUsers();
        if (users.isEmpty()){
            ResponseEntity.noContent().build();
        }
        List<@Valid UserDto> userDtos = users.stream().map(DtoConverter::convertToUserDto).collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<@Valid UserDto> getUserById(@PathVariable("userId") String userId){
        UserEntity user = userService.getUserById(userId);
        return ResponseEntity.ok(DtoConverter.convertToUserDto(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<@Valid UserDto> getUserByEmail(@PathVariable("email") String email){
        UserEntity user = userService.getUserByEmail(email);
        return ResponseEntity.ok(DtoConverter.convertToUserDto(user));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<@Valid UserDto> getCurrentUser(HttpServletRequest request) {
        UserEntity user = userService.getCurrentUser(request);
        return ResponseEntity.ok(DtoConverter.convertToUserDto(user));
    }
}

package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.controller.dto.UserDto;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import ByteBazaar.ByteBazaarBackend.utils.DtoConverter;
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
        List<UserDto> userDtos = users.stream().map(DtoConverter::convertToUserDto).collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
        UserEntity user = userService.getUserById(userId);
        return ResponseEntity.ok(DtoConverter.convertToUserDto(user));
    }
}

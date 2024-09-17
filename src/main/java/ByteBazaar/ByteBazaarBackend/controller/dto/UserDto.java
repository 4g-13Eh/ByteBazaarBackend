package ByteBazaar.ByteBazaarBackend.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "UserId can't be null or empty")
    private String userId;
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "CartId can't be null or empty")
    private String cartId;
    @NotBlank(message = "Username can't be null or empty")
    private String username;
}

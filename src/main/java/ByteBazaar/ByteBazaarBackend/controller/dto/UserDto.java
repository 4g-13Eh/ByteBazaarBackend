package ByteBazaar.ByteBazaarBackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String email;
    private String cartId;
    private String username;
}

package ByteBazaar.ByteBazaarBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDto {
    private String userId;
    private String email;
    private String cartId;
}

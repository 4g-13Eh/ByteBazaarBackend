package ByteBazaar.ByteBazaarBackend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    @NotBlank(message = "CartId can't be null or empty")
    private String cartId;
    private List<ShoppingCartItemDto> cartItems = new ArrayList<>();
}

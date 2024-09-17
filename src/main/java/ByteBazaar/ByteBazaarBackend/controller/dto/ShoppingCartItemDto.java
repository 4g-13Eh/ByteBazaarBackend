package ByteBazaar.ByteBazaarBackend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDto {
    @NotBlank(message = "Id can't be null or empty")
    private String id;
    @NotNull(message = "Cart can't be null")
    private ShoppingCartDto cart;
    @NotNull(message = "Cartitem can't be null")
    private ItemDto cartItem;
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
}

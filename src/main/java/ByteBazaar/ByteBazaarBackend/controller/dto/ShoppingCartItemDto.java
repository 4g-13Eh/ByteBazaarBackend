package ByteBazaar.ByteBazaarBackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDto {
    private String id;
    private ShoppingCartDto cart;
    private ItemDto cartItem;
    private Integer quantity;
}

package ByteBazaar.ByteBazaarBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemEntity {
    public ItemEntity cartItem;
    public Integer quantity;
}

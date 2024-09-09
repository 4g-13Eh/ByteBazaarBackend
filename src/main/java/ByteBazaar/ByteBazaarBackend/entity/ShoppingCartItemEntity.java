package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemEntity {
    @ManyToOne
    @JoinColumn(name = "item_id")
    public ItemEntity cartItem;
    public Integer quantity;
}

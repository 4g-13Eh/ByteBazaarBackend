package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shoppingcarts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntity {
    @Id
    public String cartId;
    public List<ShoppingCartItemEntity> cartItems;
}

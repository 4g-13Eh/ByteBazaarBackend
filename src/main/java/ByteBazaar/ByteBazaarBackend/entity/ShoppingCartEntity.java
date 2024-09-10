package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name = "shoppingcarts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntity {
    @Id
    @Column(name = "Id")
    @UuidGenerator
    public String cartId;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    public List<ShoppingCartItemEntity> cartItems;
}

package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntity {
    @Id
    @Column(name = "Id")
    @UuidGenerator
    private String cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ShoppingCartItemEntity> cartItems = new ArrayList<>();
}

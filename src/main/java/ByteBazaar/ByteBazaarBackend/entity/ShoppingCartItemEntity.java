package ByteBazaar.ByteBazaarBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemEntity {
    @Id
    @Column(name = "Id")
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "Id")
    @JsonIgnore // https://stackoverflow.com/a/67766027
    @ToString.Exclude
    private ShoppingCartEntity cart;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "Id")
    private ItemEntity cartItem;

    @Column(name = "quantity")
    private Integer quantity;
}

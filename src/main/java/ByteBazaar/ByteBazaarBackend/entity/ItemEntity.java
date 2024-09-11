package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @Column(name = "Id")
    @UuidGenerator
    private String itemId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private Float price;

    @Column(name = "in_stock")
    private Boolean in_stock;

    @Column(name = "stock_num")
    private Integer stock_num;

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_category",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private List<CategoryEntity> categories;
}

package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @Column(name = "Id")
    public String itemId;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "picture")
    public String picture;

    @Column(name = "price")
    public Float price;

    @Column(name = "in_stock")
    public Boolean in_stock;

    @Column(name = "stock_num")
    public Integer stock_num;

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_category",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    public List<CategoryEntity> categories;
}

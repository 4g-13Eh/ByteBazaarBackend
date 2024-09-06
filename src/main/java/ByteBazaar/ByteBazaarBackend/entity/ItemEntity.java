package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public String itemId;
    public String name;
    public String description;
    public String picture;
    public Float price;
    public Boolean in_stock;
    public Integer stock_num;
    public List<CategoryEnum> categories;
}

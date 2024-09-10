package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @Column(name = "Id")
    public Integer id;

    @Column(name = "category")
    public String categoryName;
}

package ByteBazaar.ByteBazaarBackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String itemId, name, description, picture;
    private Float price;
    private Boolean in_stock;
    private Integer stock_num;
    private List<CategoryDto> categories;
}

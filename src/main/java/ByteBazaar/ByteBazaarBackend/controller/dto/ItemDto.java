package ByteBazaar.ByteBazaarBackend.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    @NotBlank(message = "One of the following properties is null: itemId, name, description, picture. Can't be null")
    private String itemId, name, description, picture;
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @Digits(fraction = 2, message = "Price can't have more than 6 integral digits & 2 fractional digits", integer = 6)
    private Float price;
    private Boolean in_stock;
    @PositiveOrZero(message = "Stock_num must be at least 0")
    private Integer stock_num;
    @NotEmpty(message = "Categories list can't be empty")
    private List<CategoryDto> categories;
}

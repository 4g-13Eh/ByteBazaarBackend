package ByteBazaar.ByteBazaarBackend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @NotNull(message = "CategoryId can't be null")
    private Integer id;
    @NotBlank(message = "Category can't be null or empty")
    private String categoryName;
}

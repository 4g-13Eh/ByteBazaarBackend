package ByteBazaar.ByteBazaarBackend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequestDto {
    @NotBlank(message = "ItemId can't be null or empty")
    private String itemId;
}

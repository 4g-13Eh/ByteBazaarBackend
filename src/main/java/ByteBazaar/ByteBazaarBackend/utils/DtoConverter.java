package ByteBazaar.ByteBazaarBackend.utils;

import ByteBazaar.ByteBazaarBackend.controller.dto.*;
import ByteBazaar.ByteBazaarBackend.entity.*;
import jakarta.validation.Valid;

import java.util.stream.Collectors;

public class DtoConverter {


    public static @Valid ItemDto convertToItemDto(ItemEntity item) {
        return new ItemDto(item.getItemId(), item.getName(), item.getDescription(), item.getPicture(),
                item.getPrice(), item.getIn_stock(), item.getStock_num(),
                item.getCategories().stream().map(DtoConverter::convertToCategoryDto).collect(Collectors.toList()));
    }

    public static @Valid CategoryDto convertToCategoryDto(CategoryEntity category) {
        return new CategoryDto(category.getId(), category.getCategoryName());
    }

    public static @Valid ShoppingCartDto convertToShoppingCartDto(ShoppingCartEntity cart) {
        return new ShoppingCartDto(cart.getCartId(), null);
    }

    public static @Valid ShoppingCartItemDto convertToShoppingCartItemDto(ShoppingCartItemEntity cartItem) {
        return new ShoppingCartItemDto(cartItem.getId(), null,
                convertToItemDto(cartItem.getCartItem()), cartItem.getQuantity());
    }

    public static @Valid UserDto convertToUserDto(UserEntity user) {
        return new UserDto(user.getUserId(), user.getEmail(), user.getCartId(), user.getUsername());
    }
}

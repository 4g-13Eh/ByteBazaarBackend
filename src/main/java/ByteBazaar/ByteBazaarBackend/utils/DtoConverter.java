package ByteBazaar.ByteBazaarBackend.utils;

import ByteBazaar.ByteBazaarBackend.controller.dto.*;
import ByteBazaar.ByteBazaarBackend.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class DtoConverter {

    private static final Logger log = LoggerFactory.getLogger(DtoConverter.class);

    public static ItemDto convertToItemDto(ItemEntity item) {
        return new ItemDto(item.getItemId(), item.getName(), item.getDescription(), item.getPicture(),
                item.getPrice(), item.getIn_stock(), item.getStock_num(),
                item.getCategories().stream().map(DtoConverter::convertToCategoryDto).collect(Collectors.toList()));
    }

    public static CategoryDto convertToCategoryDto(CategoryEntity category) {
        return new CategoryDto(category.getId(), category.getCategoryName());
    }

    public static ShoppingCartDto convertToShoppingCartDto(ShoppingCartEntity cart) {
        return new ShoppingCartDto(cart.getCartId(), null);
    }

    public static ShoppingCartItemDto convertToShoppingCartItemDto(ShoppingCartItemEntity cartItem) {
        log.info("Converting ShoppingCartItemEntity to DTO: {}", cartItem);
        return new ShoppingCartItemDto(cartItem.getId(), null,
                convertToItemDto(cartItem.getCartItem()), cartItem.getQuantity());
    }

    public static UserDto convertToUserDto(UserEntity user) {
        return new UserDto(user.getUserId(), user.getEmail(), user.getCartId(), user.getUsername());
    }
}

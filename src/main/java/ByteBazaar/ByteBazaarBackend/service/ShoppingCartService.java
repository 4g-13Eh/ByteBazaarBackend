package ByteBazaar.ByteBazaarBackend.service;

import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartEntity createCart();
    ShoppingCartEntity getCartById(String cartId);
    List<ShoppingCartItemEntity> getCartItems(String cartId);
    void addItemToCart(String cartId, String itemId);
    void removeItemFromCart(String cartId, String cartItemId);
    void clearCart(String cartId);
    Integer getTotalQuantityForCart(String cartId);
    void updateItemQuantity(String cartId, String itemId, Integer newQuantity);

//    void updateCartItemCount(String cartId);
}

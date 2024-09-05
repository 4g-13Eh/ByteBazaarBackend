package com.ByteBazaar.ByteBazaarBackend.serivce;

import com.ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import com.ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartEntity createCart(ShoppingCartEntity cart);
    ShoppingCartEntity getCartById(String cartId);
    List<ShoppingCartItemEntity> getCartItems(String cartId);
    void addItemToCart(String cartId, List<ShoppingCartItemEntity> item);
    void removeItemFromCart(String cartId, String cartItemId);
    void clearCart(String cartId);
//    Integer getCartItemCount(String cartId);
//    void updateCartItemCount(String cartId);
//    void updateItemQuantity(String cartId, String itemId, Integer newQuantity);
}

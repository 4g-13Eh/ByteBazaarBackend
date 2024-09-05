package com.ByteBazaar.ByteBazaarBackend.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class ShoppingCartEntity {
    @Id
    public String cartId;
    public List<ShoppingCartItemEntity> cartItems;

    public String getCartId() {
        return cartId;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<ShoppingCartItemEntity> getItems() {
        return cartItems;
    }
    public void setItems(List<ShoppingCartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }
}

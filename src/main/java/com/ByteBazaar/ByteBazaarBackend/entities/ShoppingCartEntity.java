package com.ByteBazaar.ByteBazaarBackend.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class ShoppingCartEntity {
    @Id
    public String cartId;
    public List<ShoppingCartItem> cartItems;

    public String getCartId() {
        return cartId;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<ShoppingCartItem> getItems() {
        return cartItems;
    }
    public void setItems(List<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }
}

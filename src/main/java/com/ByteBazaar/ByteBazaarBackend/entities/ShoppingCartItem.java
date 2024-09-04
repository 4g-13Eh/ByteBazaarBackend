package com.ByteBazaar.ByteBazaarBackend.entities;

import java.util.List;

public class ShoppingCartItem {
    public ItemEntity cartItem;
    public Integer quantity;

    public ItemEntity getCartItem() {
        return cartItem;
    }
    public void setCartItem(ItemEntity cartItem) {
        this.cartItem = cartItem;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

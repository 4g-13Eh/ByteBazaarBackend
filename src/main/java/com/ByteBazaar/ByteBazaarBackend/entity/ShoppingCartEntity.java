package com.ByteBazaar.ByteBazaarBackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "shoppingcarts")
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

package com.ByteBazaar.ByteBazaarBackend.serivce.impl;

import com.ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import com.ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import com.ByteBazaar.ByteBazaarBackend.exception.ShoppingCartNotFoundException;
import com.ByteBazaar.ByteBazaarBackend.repository.ShoppingCartRepository;
import com.ByteBazaar.ByteBazaarBackend.serivce.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartEntity createCart(ShoppingCartEntity cart) {
        cart.setCartId(UUID.randomUUID().toString());
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCartEntity getCartById(String cartId) {
        return shoppingCartRepository.findById(cartId).orElseThrow(ShoppingCartNotFoundException::new);
    }

    @Override
    public List<ShoppingCartItemEntity> getCartItems(String cartId) {
        return getCartById(cartId).getItems();
    }

    @Override
    public void addItemToCart(String cartId, List<ShoppingCartItemEntity> item) {
        ShoppingCartEntity cart = getCartById(cartId);
        cart.setItems(item);
    }

    @Override
    public void removeItemFromCart(String cartId, String cartItemId){
        ShoppingCartEntity cart = getCartById(cartId);
        cart.getItems().removeIf(item -> item.getCartItem().getItemId().equals(cartItemId));
        shoppingCartRepository.save(cart);
    }

    @Override
    public void clearCart(String cartId){
        shoppingCartRepository.deleteById(cartId);
    }
}

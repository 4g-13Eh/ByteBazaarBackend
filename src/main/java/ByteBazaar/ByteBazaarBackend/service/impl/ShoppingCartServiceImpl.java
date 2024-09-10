package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import ByteBazaar.ByteBazaarBackend.exception.ShoppingCartNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.ShoppingCartRepository;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartEntity createCart() {
        ShoppingCartEntity cart = new ShoppingCartEntity();
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCartEntity getCartById(String cartId) {
        return shoppingCartRepository.findById(cartId).orElseThrow(ShoppingCartNotFoundException::new);
    }

    @Override
    public List<ShoppingCartItemEntity> getCartItems(String cartId) {
        return getCartById(cartId).getCartItems();
    }

    @Override
    public void addItemToCart(String cartId, List<ShoppingCartItemEntity> item) {
        ShoppingCartEntity cart = getCartById(cartId);
        cart.setCartItems(item);
    }

    @Override
    public void removeItemFromCart(String cartId, String cartItemId){
        ShoppingCartEntity cart = getCartById(cartId);
        cart.getCartItems().removeIf(item -> item.getCartItem().getItemId().equals(cartItemId));
        shoppingCartRepository.save(cart);
    }

    @Override
    public void clearCart(String cartId){
        shoppingCartRepository.deleteById(cartId);
    }
}

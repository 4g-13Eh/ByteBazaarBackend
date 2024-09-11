package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import ByteBazaar.ByteBazaarBackend.exception.ShoppingCartNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.CartItemRepository;
import ByteBazaar.ByteBazaarBackend.repository.ShoppingCartRepository;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemService itemService;

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
    public void addItemToCart(String cartId, String itemId) {
        ShoppingCartEntity cart = getCartById(cartId);
        ItemEntity item = itemService.getItemById(itemId);

        Optional<ShoppingCartItemEntity> exisitngItemOpt = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getCartItem().getItemId().equals(itemId))
                .findFirst();

        if (exisitngItemOpt.isPresent()){
            ShoppingCartItemEntity existingItem = exisitngItemOpt.get();
            Integer currentQuantity = existingItem.getQuantity();
            if (currentQuantity == null){
                currentQuantity = 0;
            }
            existingItem.setQuantity(currentQuantity + 1);
        } else {
            ShoppingCartItemEntity newCartItem = new ShoppingCartItemEntity();
            newCartItem.setCart(cart);
            newCartItem.setCartItem(item);
            newCartItem.setQuantity(1);
            cart.getCartItems().add(newCartItem);
        }
        System.out.println("Items in cart before saving: "+cart.getCartItems().size());
        shoppingCartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(String cartId, String cartItemId){
        ShoppingCartEntity cart = getCartById(cartId);
        cart.getCartItems().removeIf(item -> item.getCartItem().getItemId().equals(cartItemId));
        shoppingCartRepository.save(cart);
    }

    @Override
    public void clearCart(String cartId){
        cartItemRepository.deleteByCartId(cartId);
    }

    @Override
    public Integer getTotalQuantityForCart(String cartId){
        return cartItemRepository.getTotalQuantityByCartId(cartId);
    }
}

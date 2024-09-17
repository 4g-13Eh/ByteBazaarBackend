package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import ByteBazaar.ByteBazaarBackend.exception.ShoppingCartNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.CartItemRepository;
import ByteBazaar.ByteBazaarBackend.repository.ShoppingCartRepository;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemService itemService;

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
        log.info("Fetching cart for cartId: {}", cartId);
        log.info("Found cart: {}", getCartById(cartId));
        log.info("Cart items: {}", getCartById(cartId).getCartItems());
        return getCartById(cartId).getCartItems();
    }

    @Override
    public void addItemToCart(String cartId, String itemId) {
        ShoppingCartEntity cart = getCartById(cartId);
        ItemEntity item = itemService.getItemById(itemId);

        Optional<ShoppingCartItemEntity> existingItemOpt = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getCartItem().getItemId().equals(itemId))
                .findFirst();

        if (existingItemOpt.isPresent()){
            ShoppingCartItemEntity existingItem = existingItemOpt.get();
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
        shoppingCartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(String cartId, String cartItemId){
        ShoppingCartEntity cart = getCartById(cartId);

        Optional<ShoppingCartItemEntity> itemToRemoveOpt = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getCartItem().getItemId().equals(cartItemId))
                .findFirst();

        if (itemToRemoveOpt.isPresent()){
            ShoppingCartItemEntity itemToRemove = itemToRemoveOpt.get();
            cart.getCartItems().remove(itemToRemove);
            cartItemRepository.delete(itemToRemove);
            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public void clearCart(String cartId){
        cartItemRepository.deleteByCartId(cartId);
    }

    @Override
    public Integer getTotalQuantityForCart(String cartId){
        Integer count = cartItemRepository.getTotalQuantityByCartId(cartId);
        if (count == null){
            throw new ShoppingCartNotFoundException();
        }
        return count;
    }

    @Override
    public void updateItemQuantity(String cartId, String itemId, Integer newQuantity){
        ShoppingCartEntity cart = getCartById(cartId);
        ItemEntity item = itemService.getItemById(itemId);

        Optional<ShoppingCartItemEntity> itemToUpdateOpt = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getCartItem().getItemId().equals(item.getItemId()))
                .findFirst();

        if (itemToUpdateOpt.isPresent()){
            ShoppingCartItemEntity itemToUpdate = itemToUpdateOpt.get();
            if (newQuantity >= 0 && newQuantity <= itemToUpdate.getCartItem().getStock_num()){
                itemToUpdate.setQuantity(newQuantity);
            } else {
                itemToUpdate.setQuantity(1);
            }
        }
        shoppingCartRepository.save(cart);
    }
}

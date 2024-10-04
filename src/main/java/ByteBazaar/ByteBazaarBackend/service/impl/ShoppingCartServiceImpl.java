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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
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
        return getCartById(cartId).getCartItems();
    }

    @Override
    public void addItemToCart(String cartId, String itemId) {
        ShoppingCartEntity cart = getCartById(cartId);
        ItemEntity item = itemService.getItemById(itemId);

        if (!item.getIn_stock()) return;

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
        getCartById(cartId);
        return cartItemRepository.getTotalQuantityByCartId(cartId);
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
                itemToUpdate.setQuantity(itemToUpdate.getCartItem().getStock_num());
            }
        }
        shoppingCartRepository.save(cart);
    }
}

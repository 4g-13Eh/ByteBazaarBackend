package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.dto.AddItemRequestDto;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public ShoppingCartEntity createShoppingCart(){
        return shoppingCartService.createCart();
    }

    @GetMapping("/{cartId}")
    public List<ShoppingCartItemEntity> getCartItems(@PathVariable("cartId") String cartId){
        return shoppingCartService.getCartItems(cartId);
    }

    @PutMapping("/{cartId}")
    public void addItem(@PathVariable("cartId") String cartId, @RequestBody AddItemRequestDto addItemRequestDto){
        shoppingCartService.addItemToCart(cartId, addItemRequestDto.getItemId());
    }

    @DeleteMapping("/{cartId}")
    public void clearCart(@PathVariable("cartId") String cartId){
        shoppingCartService.clearCart(cartId);
    }

    @DeleteMapping("/{cartId}/{itemId}")
    public void removeItem(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId){
        shoppingCartService.removeItemFromCart(cartId, itemId);
    }

    @GetMapping("/quantity/{cartId}")
    public Integer getCartCount(@PathVariable("cartId") String cartId){
        return shoppingCartService.getTotalQuantityForCart(cartId);
    }
}

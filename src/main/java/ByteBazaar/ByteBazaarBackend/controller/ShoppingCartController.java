package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.controller.dto.AddItemRequestDto;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<List<ShoppingCartItemEntity>> getCartItems(@PathVariable("cartId") String cartId){
        List<ShoppingCartItemEntity> carts = shoppingCartService.getCartItems(cartId);
        if (carts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carts);
    }

    @PutMapping("/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable("cartId") String cartId, @RequestBody AddItemRequestDto addItemRequestDto){
        shoppingCartService.addItemToCart(cartId, addItemRequestDto.getItemId());
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable("cartId") String cartId){
        shoppingCartService.clearCart(cartId);
    }

    @DeleteMapping("/{cartId}/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId){
        shoppingCartService.removeItemFromCart(cartId, itemId);
    }

    @GetMapping("/quantity/{cartId}")
    public ResponseEntity<Integer> getCartCount(@PathVariable("cartId") String cartId){
        Integer count = shoppingCartService.getTotalQuantityForCart(cartId);
        return ResponseEntity.ok(count);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/quantity/{cartId}/{itemId}")
    public void updateItemQuantity(
            @PathVariable("cartId") String cartId,
            @PathVariable("itemId") String itemId,
            @RequestBody  Integer newQuantity
    ){
        shoppingCartService.updateItemQuantity(cartId, itemId, newQuantity);
    }
}

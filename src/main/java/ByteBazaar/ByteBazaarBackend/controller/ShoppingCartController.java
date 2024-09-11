package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.dto.AddItemRequestDto;
import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ShoppingCartEntity createShoppingCart(){
        return shoppingCartService.createCart();
    }

    @PutMapping("/add/{cartId}")
    public void add(@PathVariable("cartId") String cartId, @RequestBody AddItemRequestDto addItemRequestDto){
        shoppingCartService.addItemToCart(cartId, addItemRequestDto.getItemId());
    }

    @DeleteMapping("/clear/{cartId}")
    public void clear(@PathVariable("cartId") String cartId){
        shoppingCartService.clearCart(cartId);
    }

    @GetMapping("/quantity/{cartId}")
    public Integer quant(@PathVariable("cartId") String cartId){
        return shoppingCartService.getTotalQuantityForCart(cartId);
    }
}

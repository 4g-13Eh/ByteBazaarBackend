package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    List<ItemEntity> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    ItemEntity getItemById(@PathVariable("itemId") String itemId){
        return itemService.getItemById(itemId);
    }

    @PostMapping
    List<ItemEntity> filterItem(@RequestBody List<String> categories){
        return itemService.filterItemByCategories(categories);
    }
}

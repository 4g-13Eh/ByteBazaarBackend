package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllItems(){
        List<ItemEntity> items = itemService.getAllItems();
        if (items.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable("itemId") String itemId){
        ItemEntity item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<List<ItemEntity>> filterItem(@RequestBody List<String> categories){
        List<ItemEntity> filteredItems = itemService.filterItemByCategories(categories);
        if (filteredItems.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filteredItems);
    }
}

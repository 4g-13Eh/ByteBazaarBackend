package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    List<ItemEntity> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    ItemEntity getItemById(@PathVariable("itemId") String itemId){
        return itemService.getItemById(itemId);
    }
//    ToDo:
//    Endpoint 4 category-filter
}

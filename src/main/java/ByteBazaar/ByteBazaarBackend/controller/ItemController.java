package ByteBazaar.ByteBazaarBackend.controller;

import ByteBazaar.ByteBazaarBackend.controller.dto.ItemDto;
import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import ByteBazaar.ByteBazaarBackend.utils.DtoConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List< @Valid ItemDto>> getAllItems(){
        List<ItemEntity> items = itemService.getAllItems();
        if (items.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<@Valid ItemDto> itemDtos = items.stream().map(DtoConverter::convertToItemDto).collect(Collectors.toList());
        return ResponseEntity.ok(itemDtos);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<@Valid ItemDto> getItemById(@PathVariable("itemId") String itemId){
        ItemEntity item = itemService.getItemById(itemId);
        return ResponseEntity.ok(DtoConverter.convertToItemDto(item));
    }

    @PostMapping
    public ResponseEntity<List<@Valid ItemDto>> filterItem(@RequestBody List<String> categories){
        List<ItemEntity> filteredItems = itemService.filterItemByCategories(categories);
        if (filteredItems.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<@Valid ItemDto> itemDtos = filteredItems.stream().map(DtoConverter::convertToItemDto).collect(Collectors.toList());
        return ResponseEntity.ok(itemDtos);
    }
}

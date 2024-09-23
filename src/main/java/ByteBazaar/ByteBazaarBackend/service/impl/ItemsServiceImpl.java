package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEntity;
import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.exception.InsufficientStockException;
import ByteBazaar.ByteBazaarBackend.exception.ItemNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.ItemRepository;
import ByteBazaar.ByteBazaarBackend.service.CategoryService;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemService {
    private final CategoryService categoryService;
    private final ItemRepository itemRepository;

    @Override
    public List<ItemEntity> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity getItemById(String itemId){
        return itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public List<ItemEntity> filterItemByCategories(List<String> filterCategoryNames) {
        List<CategoryEntity> filterCategories = categoryService.getCategoriesByNames(filterCategoryNames);

        List<ItemEntity> items = getAllItems();
        items = items.stream()
                .filter(item -> new HashSet<>(item.getCategories()).containsAll(filterCategories))
                .collect(Collectors.toList());

        return items;
    }

    @Override
    public Integer getItemStockNum(String itemId){
        ItemEntity item = getItemById(itemId);
        return item.getStock_num();
    }

    @Override
    public void decreaseItemStock(String itemId, Integer quantity){
        ItemEntity item = getItemById(itemId);
        if (item.getStock_num() >= quantity){
            item.setStock_num(item.getStock_num() - quantity);
        } else {
            throw new InsufficientStockException();
        }
    }
}

package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEntity;
import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.exception.ItemNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.ItemRepository;
import ByteBazaar.ByteBazaarBackend.service.CategoryService;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .filter(item -> item.getCategories().containsAll(filterCategories))
                .collect(Collectors.toList());

        return items;
    }
}

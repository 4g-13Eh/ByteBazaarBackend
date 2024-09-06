package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEnum;
import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import ByteBazaar.ByteBazaarBackend.exception.ItemNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.ItemRepository;
import ByteBazaar.ByteBazaarBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemEntity> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity getItemById(String itemId){
        return itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public List<ItemEntity> filterItemByCategories(List<CategoryEnum> filterCategories){
        List<ItemEntity> items = getAllItems();
        items = items.stream()
                .filter(item -> item.getCategories().containsAll(filterCategories))
                .collect(Collectors.toList());
        return items;
    }
}

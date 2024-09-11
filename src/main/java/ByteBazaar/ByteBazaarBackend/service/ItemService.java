package ByteBazaar.ByteBazaarBackend.service;

import ByteBazaar.ByteBazaarBackend.entity.ItemEntity;

import java.util.List;

public interface ItemService {
    List<ItemEntity> getAllItems();
    ItemEntity getItemById(String itemId);
    List<ItemEntity> filterItemByCategories(List<String> categories);
}

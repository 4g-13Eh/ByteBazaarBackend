package com.ByteBazaar.ByteBazaarBackend.serivce;

import com.ByteBazaar.ByteBazaarBackend.entity.CategoryEnum;
import com.ByteBazaar.ByteBazaarBackend.entity.ItemEntity;

import java.util.List;

public interface ItemService {
    List<ItemEntity> getAllItems();
    ItemEntity getItemById(String itemId);
    List<ItemEntity> filterItemByCategories(List<CategoryEnum> categories);
}

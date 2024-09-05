package com.ByteBazaar.ByteBazaarBackend.serivce.impl;

import com.ByteBazaar.ByteBazaarBackend.entity.CategoryEnum;
import com.ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import com.ByteBazaar.ByteBazaarBackend.exception.ItemNotFoundException;
import com.ByteBazaar.ByteBazaarBackend.repository.ItemRepository;
import com.ByteBazaar.ByteBazaarBackend.serivce.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {

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

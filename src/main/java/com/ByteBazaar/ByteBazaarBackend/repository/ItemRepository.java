package com.ByteBazaar.ByteBazaarBackend.repository;

import com.ByteBazaar.ByteBazaarBackend.entity.ItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<ItemEntity, String> {
}

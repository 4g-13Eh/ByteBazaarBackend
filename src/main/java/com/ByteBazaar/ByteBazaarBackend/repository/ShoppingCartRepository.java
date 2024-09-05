package com.ByteBazaar.ByteBazaarBackend.repository;

import com.ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String> {
}

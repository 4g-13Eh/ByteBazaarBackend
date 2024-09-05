package com.ByteBazaar.ByteBazaarBackend.repository;

import com.ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}

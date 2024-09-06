package com.ByteBazaar.ByteBazaarBackend.serivce;

import com.ByteBazaar.ByteBazaarBackend.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity getUserById(String userId);
    UserEntity assignCartToUser(String userId, String cartId);
}

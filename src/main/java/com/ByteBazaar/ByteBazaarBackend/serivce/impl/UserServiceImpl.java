package com.ByteBazaar.ByteBazaarBackend.serivce.impl;

import com.ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import com.ByteBazaar.ByteBazaarBackend.exception.UserNotFoundException;
import com.ByteBazaar.ByteBazaarBackend.repository.UserRepository;
import com.ByteBazaar.ByteBazaarBackend.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user){
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserById(String id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserEntity assignCartToUser(String userId, String cartId){
        UserEntity user = this.getUserById(userId);
        user.setCartId(cartId);
        return userRepository.save(user);
    }
}

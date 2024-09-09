package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.exception.UserNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.UserRepository;
import ByteBazaar.ByteBazaarBackend.service.UserService;
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

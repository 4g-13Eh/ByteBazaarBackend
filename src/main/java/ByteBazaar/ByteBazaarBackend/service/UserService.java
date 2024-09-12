package ByteBazaar.ByteBazaarBackend.service;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(String email, String passwordHash, String confirmedPassword);
    Boolean loginUser(String email, String password);
    UserEntity getUserById(String userId);
    UserEntity assignCartToUser(String userId, String cartId);
    List<UserEntity> getAllUsers();
}

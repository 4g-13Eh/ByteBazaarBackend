package ByteBazaar.ByteBazaarBackend.service;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    UserEntity createUser(String email, String passwordHash, String confirmedPassword);
    UserEntity getUserById(String userId);
    UserEntity getUserByEmail(String email);
    List<UserEntity> getAllUsers();
    UserEntity getCurrentUser(HttpServletRequest request);
}

package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.exception.InvalidEmailOrPasswordException;
import ByteBazaar.ByteBazaarBackend.exception.UserAlreadyExistsException;
import ByteBazaar.ByteBazaarBackend.exception.UserNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.UserRepository;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(String email, String passwordHash){
        if (email == null || email.isBlank() || passwordHash == null || passwordHash.isBlank()){
            throw new InvalidEmailOrPasswordException();
        } else if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPasswordHash(passwordHash);
        user.setCartId(null);
        return userRepository.save(user);
    }

    @Override
    public Boolean loginUser(String email, String password){
        if (email == null || email.isBlank() || password == null || password.isBlank()){
            throw new InvalidEmailOrPasswordException();
        }
        Optional<UserEntity> usr = userRepository.findByEmail(email);
        if (!usr.get().getPasswordHash().equals(password)){
            throw new InvalidEmailOrPasswordException();
        }
        return true;
    }

    @Override
    public UserEntity getUserById(String id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserEntity assignCartToUser(String userId, String cartId){
        UserEntity user = this.getUserById(userId);
        user.setCartId(cartId);
        return userRepository.save(user);
    }
}

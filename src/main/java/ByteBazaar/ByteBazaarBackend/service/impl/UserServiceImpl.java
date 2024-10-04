package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import ByteBazaar.ByteBazaarBackend.exception.InvalidEmailOrPasswordException;
import ByteBazaar.ByteBazaarBackend.exception.UserAlreadyExistsException;
import ByteBazaar.ByteBazaarBackend.exception.UserNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.UserRepository;
import ByteBazaar.ByteBazaarBackend.security.service.JwtService;
import ByteBazaar.ByteBazaarBackend.service.ShoppingCartService;
import ByteBazaar.ByteBazaarBackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;
    private final JwtService jwtService;

    @Override
    public UserEntity createUser(String email, String passwordHash, String confirmedPassword){
        String emailRegex = "^(?!.*\\.\\..*)(?!.*\\.$)(?!^\\.)[\\p{L}0-9._-]{1,64}@[A-Za-z0-9][A-Za-z0-9.-]{0,253}[A-Za-z0-9]\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (email.isBlank() || !emailMatcher.matches() ||
                passwordHash == null || passwordHash.isBlank() ||
                confirmedPassword == null || confirmedPassword.isBlank() || !confirmedPassword.equals(passwordHash)
        ){
            throw new InvalidEmailOrPasswordException();
        } else if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserEntity user = new UserEntity();
        ShoppingCartEntity cart = shoppingCartService.createCart();
        user.setEmail(email);
        passwordHash = passwordEncoder.encode(passwordHash);
        user.setPasswordHash(passwordHash);
        user.setCartId(cart.getCartId());
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserById(String id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserEntity getCurrentUser(HttpServletRequest request){
        String accessToken = jwtService.getTokenFromRequest(request);

        if (accessToken != null && jwtService.isTokenValid(accessToken)){
            String email = jwtService.extractUsername(accessToken);
            return getUserByEmail(email);
        }

        return null;
    }

}

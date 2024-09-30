package ByteBazaar.ByteBazaarBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cart not found")
public class ShoppingCartNotFoundException extends RuntimeException{}

package ByteBazaar.ByteBazaarBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Token not found")
public class TokenNotFoundException extends RuntimeException{
}

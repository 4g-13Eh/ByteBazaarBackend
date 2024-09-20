package ByteBazaar.ByteBazaarBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Access denied, unauthorized")
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("Unauthorized access.");
    }
}

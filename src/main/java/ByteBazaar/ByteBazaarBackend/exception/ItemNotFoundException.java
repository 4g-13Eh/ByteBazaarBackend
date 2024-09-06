package ByteBazaar.ByteBazaarBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Item not found")
public class ItemNotFoundException extends RuntimeException {}

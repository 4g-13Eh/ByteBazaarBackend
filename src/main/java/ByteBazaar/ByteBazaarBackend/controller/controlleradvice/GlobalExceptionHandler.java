package ByteBazaar.ByteBazaarBackend.controller.controlleradvice;

import ByteBazaar.ByteBazaarBackend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<String> handleInvalidEmailOrPassword(InvalidEmailOrPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password.");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFound(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }

    @ExceptionHandler(ShoppingCartNotFoundException.class)
    public ResponseEntity<String> handleShoppingCartNotFound(ShoppingCartNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidRequestBody(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid request body. Please provide a valid quantity in numeric format.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid parameter. Please provide a valid numeric value.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<String> handleBadCredentials(InternalAuthenticationServiceException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An unexpected error occurred: Bad credentials");
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<String> handleTokenNotFound(TokenNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found");
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStock(InsufficientStockException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to decrease.");
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<String> handleInvalidQuantity(InvalidQuantityException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity. Must be a positive integer value");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}

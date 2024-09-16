package ByteBazaar.ByteBazaarBackend.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
        super("Access denied.");
    }
}

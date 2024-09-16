package ByteBazaar.ByteBazaarBackend.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("Unauthorized access.");
    }
}

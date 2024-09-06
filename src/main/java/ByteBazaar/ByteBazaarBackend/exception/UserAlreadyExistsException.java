package ByteBazaar.ByteBazaarBackend.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String msg){
        super(msg);
    }

    public UserAlreadyExistsException(){}
}

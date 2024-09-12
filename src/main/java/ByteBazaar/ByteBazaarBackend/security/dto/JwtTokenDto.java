package ByteBazaar.ByteBazaarBackend.security.dto;

public class JwtTokenDto {
    public String token;

    public JwtTokenDto(String token){
        this.token = token;
    }
}

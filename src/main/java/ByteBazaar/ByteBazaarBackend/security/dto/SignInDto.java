package ByteBazaar.ByteBazaarBackend.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @Email(message = "Email must be in correct format")
    private String email;
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;
}

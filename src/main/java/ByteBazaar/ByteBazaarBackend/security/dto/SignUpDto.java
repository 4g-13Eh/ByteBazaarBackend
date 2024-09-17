package ByteBazaar.ByteBazaarBackend.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
   @Email(message = "Email must have valid format")
   private String email;
   @Size(min = 8, message = "Password must be at least 8 characters long")
   private String password, confirmedPassword;
}

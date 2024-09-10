package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "Id")
    @UuidGenerator
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String passwordHash;
    @Column(name = "cartId")
    private String cartId;
}

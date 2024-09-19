package ByteBazaar.ByteBazaarBackend.entity;

import ByteBazaar.ByteBazaarBackend.enumeration.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "token", unique = true)
    private String token;
    @Column(name = "is_expired")
    private boolean expired;
    @Column(name = "is_revoked")
    private boolean revoked;
    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
}

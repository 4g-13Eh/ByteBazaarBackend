package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;
}

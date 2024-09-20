package ByteBazaar.ByteBazaarBackend.repository;

import ByteBazaar.ByteBazaarBackend.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    @Query("""
            SELECT t from TokenEntity t inner join UserEntity u ON t.user.id = u.id
            WHERE u.id = :userId AND (t.expired = false AND t.revoked = false)
            """)
    List<TokenEntity> findAllValidTokensByUser(String userId);

    @Query("""
            SELECT t from TokenEntity t inner join UserEntity u ON t.user.id = u.id
            WHERE u.id = :userId AND (t.expired = false AND t.revoked = false AND t.tokenType = ACCESS)
            """)
    List<TokenEntity> findAllValidAccessTokensByUser(String userId);

    Optional<TokenEntity> findByToken(String token);
}

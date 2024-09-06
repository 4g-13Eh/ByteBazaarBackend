package ByteBazaar.ByteBazaarBackend.repository;

import ByteBazaar.ByteBazaarBackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}

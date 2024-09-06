package ByteBazaar.ByteBazaarBackend.repository;

import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, String> {
}

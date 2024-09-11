package ByteBazaar.ByteBazaarBackend.repository;

import ByteBazaar.ByteBazaarBackend.entity.ShoppingCartItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<ShoppingCartItemEntity, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ShoppingCartItemEntity c WHERE c.cart.cartId = :cartId")
    void deleteByCartId(@Param("cartId") String cartId);

    @Query("SELECT SUM(c.quantity) FROM ShoppingCartItemEntity c WHERE c.cart.cartId = :cartId")
    Integer getTotalQuantityByCartId(@Param("cartId") String cartId);
}

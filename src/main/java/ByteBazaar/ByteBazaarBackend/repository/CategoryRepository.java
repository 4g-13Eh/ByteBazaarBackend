package ByteBazaar.ByteBazaarBackend.repository;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryName(String categoryName);
}

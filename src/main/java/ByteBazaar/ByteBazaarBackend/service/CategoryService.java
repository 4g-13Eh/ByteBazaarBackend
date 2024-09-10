package ByteBazaar.ByteBazaarBackend.service;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getCategoriesByNames(List<String> categoryNames);
}

package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEntity;
import ByteBazaar.ByteBazaarBackend.repository.CategoryRepository;
import ByteBazaar.ByteBazaarBackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getCategoriesByNames(List<String> categoryNames) {
        return categoryNames.stream()
                .map(categoryName -> categoryRepository.findByCategoryName(categoryName))
                .collect(Collectors.toList());
    }
}

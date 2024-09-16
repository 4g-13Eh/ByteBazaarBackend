package ByteBazaar.ByteBazaarBackend.service.impl;

import ByteBazaar.ByteBazaarBackend.entity.CategoryEntity;
import ByteBazaar.ByteBazaarBackend.repository.CategoryRepository;
import ByteBazaar.ByteBazaarBackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getCategoriesByNames(List<String> categoryNames) {
        return categoryNames.stream()
                .map(categoryRepository::findByCategoryName)
                .collect(Collectors.toList());
    }
}

package ByteBazaar.ByteBazaarBackend.converter;

import ByteBazaar.ByteBazaarBackend.enumeration.CategoryEnum;
import jakarta.persistence.AttributeConverter;

public class CategoryEnumConverter implements AttributeConverter<CategoryEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CategoryEnum categoryEnum){
        if (categoryEnum == null){
            return null;
        }
        return categoryEnum.getId();
    }

    @Override
    public CategoryEnum convertToEntityAttribute(Integer dbData){
        if (dbData == null){
            return null;
        }

        for (CategoryEnum category : CategoryEnum.values()){
            if (category.getId().equals(dbData)){
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown database value for CategoryEnum: " + dbData);
    }
}

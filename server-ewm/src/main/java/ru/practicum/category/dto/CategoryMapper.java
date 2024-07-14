package ru.practicum.category.dto;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.category.model.Category;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    Category toCategory(NewCategoryDto source);

    Category toCategory(CategoryDto source);

    CategoryDto toCategoryDto(Category source);

    List<CategoryDto> toCategoryDtoList(List<Category> source);
}

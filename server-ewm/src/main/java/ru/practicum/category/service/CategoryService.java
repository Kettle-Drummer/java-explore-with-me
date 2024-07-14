package ru.practicum.category.service;

import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto add(NewCategoryDto newCategoryDto);

    CategoryDto update(CategoryDto categoryDto, Long Id);

    void delete(Long Id);

    CategoryDto getById(Long Id);

    List<CategoryDto> getAll(Integer from, Integer size);
}

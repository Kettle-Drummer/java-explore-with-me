package ru.practicum.category.service;

import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto add(NewCategoryDto newCategoryDto);

    CategoryDto update(CategoryDto categoryDto, Long id);

    void delete(Long id);

    CategoryDto getById(Long id);

    List<CategoryDto> getAll(Integer from, Integer size);
}

package ru.practicum.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.dto.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.storage.CategoryRepository;
import ru.practicum.error.ConflictException;
import ru.practicum.error.NotFoundException;
import ru.practicum.event.storage.EventRepository;


import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryDto add(NewCategoryDto newCategoryDto) {
        Category newCategory = categoryMapper.toCategory(newCategoryDto);
        Category createdCategory = categoryRepository.save(newCategory);

        log.info("Category has been created={}", createdCategory);
        return categoryMapper.toCategoryDto(createdCategory);
    }

    @Transactional
    @Override
    public CategoryDto update(CategoryDto categoryDto, Long catId) {
        checkCategoryExists(catId);

        categoryDto.setId(catId);
        Category category = categoryMapper.toCategory(categoryDto);
        Category updatedCategory = categoryRepository.saveAndFlush(category);

        log.info("Category updated={}", updatedCategory);
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Transactional
    @Override
    public void delete(Long catId) {
        checkCategoryExists(catId);
        checkEventsAssociated(catId);

        categoryRepository.deleteById(catId);
        log.info("Category with id={} deleted", catId);
    }



    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> getAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Category> allCategories = categoryRepository.findAll(pageable).getContent();

        log.info("Received categories, size={}", allCategories.size());
        return categoryMapper.toCategoryDtoList(allCategories);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDto getById(Long catId) {
        Category category = getCategory(catId);

        log.info("Received category={} by id={}", category, catId);
        return categoryMapper.toCategoryDto(category);
    }

    private void checkCategoryExists(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            log.warn("Category with id={} catId was not found", catId);
            throw new NotFoundException("Category with id=" + catId + " was not found",
                    Collections.singletonList("Category id does not exist"));
        }
    }

    private void checkEventsAssociated(Long catId) {
        if (eventRepository.existsByCategory_Id(catId)) {
            log.warn("The category id={} is not empty", catId);
            throw new ConflictException("The category is not empty",
                    Collections.singletonList("No events should be associated with the category"));
        }
    }

    private Category getCategory(Long catId) {
        return categoryRepository.findById(catId).orElseThrow(() ->
                new NotFoundException("Category with id=" + catId + " was not found",
                        Collections.singletonList("Category id does not exist")));
    }
}

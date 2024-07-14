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
    public CategoryDto update(CategoryDto categoryDto, Long Id) {
        checkCategoryExists(Id);

        categoryDto.setId(Id);
        Category category = categoryMapper.toCategory(categoryDto);
        Category updatedCategory = categoryRepository.saveAndFlush(category);

        log.info("Category updated={}", updatedCategory);
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Transactional
    @Override
    public void delete(Long Id) {
        checkCategoryExists(Id);
        checkEventsAssociated(Id);

        categoryRepository.deleteById(Id);
        log.info("Category with id={} deleted", Id);
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
    public CategoryDto getById(Long Id) {
        Category category = getCategory(Id);

        log.info("Received category={} by id={}", category, Id);
        return categoryMapper.toCategoryDto(category);
    }

    private void checkCategoryExists(Long Id) {
        if (!categoryRepository.existsById(Id)) {
            log.warn("Category with id={} catId was not found", Id);
            throw new NotFoundException("Category with id=" + Id + " was not found",
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

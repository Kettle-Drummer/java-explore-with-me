package ru.practicum.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.service.CategoryService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@Slf4j
public class PublicCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAll(@RequestParam(defaultValue = "0", required = false) Integer from,
                                           @RequestParam(defaultValue = "10", required = false) Integer size) {
        log.info("GET /categories: request get categories, from={}, size={}", from, size);
        return categoryService.getAll(from, size);
    }

    @GetMapping("/{Id}")
    public CategoryDto getById(@PathVariable @NotNull @Min(1L) Long id) {
        log.info("GET /categories/{catId}: request get category by id={}", id);
        return categoryService.getById(id);
    }
}

package ru.practicum.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto add(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("POST /admin/categories: request create category={}", newCategoryDto);
        return categoryService.add(newCategoryDto);
    }

    @PatchMapping("/{Id}")
    public CategoryDto update(@Valid @RequestBody CategoryDto categoryDto,
                              @PathVariable @NotNull @Min(1L) Long Id) {
        log.info("PATCH /admin/categories/{catId}: request update category={} by id={}", categoryDto, Id);
        return categoryService.update(categoryDto, Id);
    }

    @DeleteMapping("/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Min(1L) Long Id) {
        log.info("DELETE /admin/categories/{catId}: request delete category by id={}", Id);
        categoryService.delete(Id);
    }
}

package ru.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.dto.NewUserRequest;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class AdminUserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@Valid @RequestBody NewUserRequest newUserRequest) {
        log.info("POST /admin/users: request create user={}", newUserRequest);
        return userService.add(newUserRequest);
    }

    @GetMapping
    public List<UserDto> getAll(@RequestParam(required = false) List<Long> ids,
                                  @RequestParam(defaultValue = "0", required = false) Integer from,
                                  @RequestParam(defaultValue = "10", required = false) Integer size) {
        log.info("GET /admin/users: request get users by ids={}, from={}, size={}", ids, from, size);
        return userService.getAll(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Min(1L) Long userId) {
        log.info("DELETE /admin/users/{userId}: request delete user by id={}", userId);
        userService.delete(userId);
    }
}

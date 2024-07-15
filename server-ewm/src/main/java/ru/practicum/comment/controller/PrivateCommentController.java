package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.dto.NewCommentDto;
import ru.practicum.comment.dto.UpdateCommentDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Validated
@Slf4j
public class PrivateCommentController {
    private final CommentService commentService;

    @PostMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto add(@PathVariable @NotNull @Positive Long eventId,
                                    @RequestParam @NotNull @Positive Long userId,
                                    @Valid @RequestBody NewCommentDto newCommentDto) {
        log.info("POST /comment/event/{eventId}: request to create a comment for event id={} by user id={}",
                eventId, userId);
        return commentService.add(eventId, userId, newCommentDto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto update(@PathVariable @NotNull @Positive Long commentId,
                                    @RequestParam @NotNull @Positive Long userId,
                                    @Valid @RequestBody UpdateCommentDto updateCommentDto) {
        log.info("PATCH /comment/{commentId}: request to update a comment id={} by user id={}", commentId, userId);
        return commentService.update(commentId, userId, updateCommentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long commentId,
                              @RequestParam @NotNull @Positive Long userId) {
        log.info("DELETE /comment/{commentId}: request to delete a comment id={} by user id={}", commentId, userId);
        commentService.delete(commentId, userId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentDto> getAllByUser(@PathVariable @NotNull @Positive Long userId,
                                               @RequestParam(defaultValue = "0", required = false) Integer from,
                                               @RequestParam(defaultValue = "10", required = false) Integer size) {
        log.info("GET /comment/user/{userId}: request to get all comments by user id={}, from={}, size={}",
                userId, from, size);
        return commentService.getAllByUser(userId, from, size);
    }

    @GetMapping("/{commentId}")
    public CommentDto getByIdByUser(@PathVariable @NotNull @Positive Long commentId,
                                 @RequestParam @NotNull @Positive Long userId) {
        log.info("GET /comment/{commentId}: request to get one comment id={} by user id={}", commentId, userId);
        return commentService.getByIdByUser(commentId, userId);
    }
}

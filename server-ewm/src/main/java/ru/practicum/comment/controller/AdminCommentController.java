package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentFullDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/admin/comment")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminCommentController {
    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAdmin(@PathVariable @NotNull @Positive Long commentId) {
        log.info("DELETE /admin/comment/{commentId}: request to delete comment id={} by administrator", commentId);
        commentService.deleteByAdmin(commentId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentFullDto> getAllByUserIdByAdmin(@PathVariable @NotNull @Positive Long userId,
                                                           @RequestParam(defaultValue = "0", required = false)
                                                           Integer from,
                                                           @RequestParam(defaultValue = "10", required = false)
                                                           Integer size) {
        log.info("GET /admin/comment/user/{userId}: request to receive all comments from user id={} by administrator," +
                        " from={}, size={}", userId, from, size);
        return commentService.getAllByUserIdByAdmin(userId, from, size);
    }

    @GetMapping("/{commentId}")
    public CommentFullDto getByIdByAdmin(@PathVariable @NotNull @Positive Long commentId) {
        log.info("GET /admin/comment/{commentId}: request to receive one comment id={} from the " +
                        "administrator", commentId);
        return commentService.getByIdByAdmin(commentId);
    }

    @GetMapping
    public List<CommentFullDto> searchByTextByAdmin(@RequestParam @NotBlank String text,
                                                         @RequestParam(defaultValue = "0", required = false)
                                                             Integer from,
                                                         @RequestParam(defaultValue = "10", required = false)
                                                             Integer size) {
        log.info("GET /admin/comment: request to receive all comments by text={} by administrator, from={}, size={}",
                text, from, size);
        return commentService.searchByTextByAdmin(text, from, size);
    }
}

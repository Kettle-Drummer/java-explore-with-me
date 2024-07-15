package ru.practicum.comment.service;

import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.dto.CommentFullDto;
import ru.practicum.comment.dto.NewCommentDto;
import ru.practicum.comment.dto.UpdateCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto add(Long eventId, Long userId, NewCommentDto newCommentDto);

    CommentDto update(Long commentId, Long userId, UpdateCommentDto updateCommentDto);

    void delete(Long commentId, Long userId);

    List<CommentDto> getAllByUser(Long userId, Integer from, Integer size);

    CommentDto getByIdByUser(Long commentId, Long usrId);

    List<CommentDto> getAllByEventId(Long eventId, Integer from, Integer size);

    void deleteByAdmin(Long commentId);

    List<CommentFullDto> getAllByUserIdByAdmin(Long userId, Integer from, Integer size);

    CommentFullDto getByIdByAdmin(Long commentId);

    List<CommentFullDto> searchByTextByAdmin(String text, Integer from, Integer size);
}

package ru.practicum.comment.dto;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.comment.model.Comment;
import ru.practicum.event.model.Event;
import ru.practicum.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", expression = "java(newComment.getText())")
    @Mapping(source = "user", target = "author")
    @Mapping(source = "event", target = "event")
    @Mapping(source = "parentComment", target = "parentComment")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    Comment toComment(NewCommentDto newComment, Event event, User user, Comment parentComment);

    @Mapping(target = "text", expression = "java(updateCommentDto.getText())")
    Comment toComment(UpdateCommentDto updateCommentDto, Comment oldComment);

    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CommentFullDto toCommentFullDto(Comment source);

    List<CommentFullDto> toCommentFullDtoList(List<Comment> source);

    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CommentDto toCommentDto(Comment source);

    List<CommentDto> toCommentDtoList(List<Comment> source);
}

package ru.practicum.comment.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private String userName;
    private String created;
    private String updated;
    private CommentDto reply;
}

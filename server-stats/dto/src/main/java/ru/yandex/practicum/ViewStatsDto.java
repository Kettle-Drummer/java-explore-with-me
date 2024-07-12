package ru.yandex.practicum;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewStatsDto {
    private String app;
    private String uri;
    private Long hits;
}

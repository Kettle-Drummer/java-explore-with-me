package ru.practicum.event.service;

import ru.practicum.event.dto.*;
import ru.practicum.event.sort.SortEvent;
import ru.practicum.event.state.EventState;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventFullDto add(Long userId, NewEventDto newEventDto);

    EventFullDto updateOwner(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest);

    EventFullDto updateAdmin(UpdateEventAdminRequest updateEventAdminRequest, Long eventId);

    EventFullDto getByIdByOwner(Long userId, Long eventId);

    EventFullDto getByRequest(Long id, HttpServletRequest request);

    List<EventShortDto> getByOwnerId(Long userId, Integer from, Integer size);

    List<EventFullDto> getAllAdmin(List<Long> users, List<EventState> states, List<Long> categories,
                                         LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                         Integer from, Integer size);

    List<EventShortDto> getAll(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd, Boolean onlyAvailable, SortEvent sort,
                                  Integer from, Integer size, HttpServletRequest request);
}

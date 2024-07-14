package ru.practicum.participation.service;

import ru.practicum.participation.dto.EventRequestStatusUpdateRequest;
import ru.practicum.participation.dto.EventRequestStatusUpdateResult;
import ru.practicum.participation.dto.ParticipationRequestDto;

import java.util.List;

public interface ParticipationRequestService {
    ParticipationRequestDto addForEventId(Long userId, Long eventId);

    EventRequestStatusUpdateResult updateStatusForEventByOwnerAndID(
            Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatus);

    ParticipationRequestDto cancelForEventId(Long userId, Long requestId);

    List<ParticipationRequestDto> getForOwnerEvent(Long userId, Long eventId);

    List<ParticipationRequestDto> getForUserForOthersEvents(Long userId);
}

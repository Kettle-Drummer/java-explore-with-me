package ru.practicum.participation.dto;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.participation.model.ParticipationRequest;

import java.util.List;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ParticipationRequestMapper {
    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
     ParticipationRequestDto toParticipationRequestDto(ParticipationRequest source);

     List<ParticipationRequestDto> toParticipationRequestDtoList(List<ParticipationRequest> source);
}

package ru.practicum.location.dto;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.location.model.Location;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface LocationMapper {

    Location toLocation(LocationDto source);

    LocationDto toLocationDto(Location source);
}

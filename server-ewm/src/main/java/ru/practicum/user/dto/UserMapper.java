package ru.practicum.user.dto;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface UserMapper {

    User toUser(NewUserRequest source);

    UserDto toUserDto(User source);

    UserShortDto toUserShortDto(User source);

    List<UserDto> toUserDtoList(List<User> users);
}
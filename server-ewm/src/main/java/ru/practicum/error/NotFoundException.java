package ru.practicum.error;

import lombok.Getter;

import java.util.List;

@Getter
public class NotFoundException extends RuntimeException {
    private final List<String> errorMessages;

    public NotFoundException(final String message, final List<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

}

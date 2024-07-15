package ru.practicum.error;

import lombok.Getter;

import java.util.List;

@Getter
public class ConflictException extends RuntimeException {
    private final List<String> errorMessages;

    public ConflictException(final String message, final List<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

}

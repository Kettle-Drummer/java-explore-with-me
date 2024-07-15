package ru.practicum.error;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<String> errorMessages;

    public ValidationException(final String message, final List<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

}

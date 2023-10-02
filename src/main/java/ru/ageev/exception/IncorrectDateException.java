package ru.ageev.exception;

public class IncorrectDateException extends Exception {
    private final String message = "Некорректный формат даты";

    @Override
    public String getMessage() {
        return message;
    }
}

package ru.ageev.exception;

public class IncorrectStartEndDateException extends Exception {
    private final String message = "Дата начала не может быть позднее даты окончания";

    @Override
    public String getMessage() {
        return message;
    }
}

package ru.ageev.exception;

public class IncorrectDateException extends Exception {
    private final String incorrectStartDate = "Дата начала не может быть позднее даты окончания";
    private final String incorrectFormat = "Некорректный формат даты";

    public String getIncorrectStartDate() {
        return incorrectStartDate;
    }

    public String getIncorrectFormat() {
        return incorrectFormat;
    }
}

package ru.ageev.service;

import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;

import java.util.Date;

public class DataValidator {
    private static final String regex = "\\d{4}-\\d{2}-\\d{2}";

    public static void checkValid(Date startDate, Date endDate) throws IncorrectDateException, IncorrectStartEndDateException {

        if (!startDate.toString().matches(regex) || !endDate.toString().matches(regex)) {
            throw new IncorrectDateException();
        }

        if (endDate.before(startDate)) {
            throw new IncorrectStartEndDateException();
        }
    }
}

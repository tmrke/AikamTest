package ru.ageev.service;

import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;

import java.io.IOException;
import java.sql.SQLException;

public interface Service {
    public void startProgram(String input, String output) throws IOException, SQLException, IncorrectStartEndDateException, IncorrectDateException;
}

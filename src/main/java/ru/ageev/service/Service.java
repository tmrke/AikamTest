package ru.ageev.service;

import java.io.IOException;
import java.sql.SQLException;

public interface Service {
    public void startProgram(String input, String output) throws IOException, SQLException;
}

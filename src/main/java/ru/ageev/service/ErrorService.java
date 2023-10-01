package ru.ageev.service;

import ru.ageev.json_convertor.WriterResult;
import ru.ageev.models.result.ErrorResult;

import java.io.IOException;
import java.sql.SQLException;

public class ErrorService implements Service {
    private WriterResult writerResult;
    private String type;

    public ErrorService(String type) {
        this.type = type;
    }

    @Override
    public void startProgram(String input, String output) throws IOException, SQLException {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage("Неизвестый тип");

        writerResult = new WriterResult(errorResult);
        writerResult.writeOutputFile(output);
    }
}

package ru.ageev.json_parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.models.result.Result;
import ru.ageev.models.result.SearchResult;
import ru.ageev.models.result.StatisticResult;

import java.io.File;
import java.io.IOException;

public class WriteResult {
    private Result result;

    public WriteResult(SearchResult result) {
        this.result = result;
    }

    public WriteResult(StatisticResult result) {
        this.result = result;
    }

    public void writeOutputFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(fileName), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

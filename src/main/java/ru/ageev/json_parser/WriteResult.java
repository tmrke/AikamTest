package ru.ageev.json_parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.models.SearchResult;

import java.io.File;
import java.io.IOException;

public class WriteResult {
    private SearchResult searchResult;

    public WriteResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public void writeOutputFile(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(fileName), searchResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

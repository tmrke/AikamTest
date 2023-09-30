package ru.ageev.json_parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.searcher.SearchResult;

import java.io.File;
import java.io.IOException;

public class WriteResult {
    private SearchResult searchResult;

    public WriteResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public void writeOutputFile(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("output.json"), searchResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

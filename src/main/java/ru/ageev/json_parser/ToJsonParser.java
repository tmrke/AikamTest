package ru.ageev.json_parser;

import ru.ageev.models.Customer;

import java.util.List;

public class ToJsonParser {
    private List<List<Customer>> searchResult;

    public ToJsonParser(List<List<Customer>> searchResult) {
        this.searchResult = searchResult;
    }


}

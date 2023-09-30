package ru.ageev;

import ru.ageev.service.SearchService;
import java.sql.*;


public class App {
    public static void main(String[] args) throws SQLException {
        String uri = "src/main/resources/criteria/criteria1.json";

        SearchService searchService = new SearchService();
        searchService.search(uri);

        System.out.println("success");
    }
}




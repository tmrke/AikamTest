package ru.ageev;

import ru.ageev.service.SearchService;
import ru.ageev.service.StatistService;

import java.io.IOException;
import java.sql.*;


public class App {
    public static void main(String[] args) throws SQLException, IOException {
        String inputFileName1 = "criteria1.json";
        String outputFileName1 = "output1.json";

        SearchService searchService = new SearchService();
        searchService.search(inputFileName1, outputFileName1);

        String inputFileName2 = "criteria2.json";
        String outputFileName2 = "output2.json";

        StatistService statistService = new StatistService();
        statistService.calculateStatistics(inputFileName2, outputFileName2);

        System.out.println("success");
    }
}
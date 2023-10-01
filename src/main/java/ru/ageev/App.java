package ru.ageev;

import ru.ageev.service.SearchService;
import ru.ageev.service.Service;
import ru.ageev.service.StatistService;
import ru.ageev.service.Type;

import java.io.IOException;
import java.sql.*;


public class App {
    public static void main(String[] args) throws SQLException, IOException {
        String inputFileName1 = "criteria1.json";
        String outputFileName1 = "output1.json";
        String searchType = Type.search.name();

        String inputFileName2 = "criteria2.json";
        String outputFileName2 = "output2.json";
        String statisticsType = Type.stat.name();

        Service service;

        String type = searchType;

        switch (type) {
            case "search" -> service = new SearchService();
            case "stat" -> service = new StatistService();

            default -> throw new IllegalStateException("Неизвестный тип: " + type);
        }

        service.startProgram(inputFileName1, outputFileName1);

        System.out.println("success");
    }
}
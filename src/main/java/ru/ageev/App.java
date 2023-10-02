package ru.ageev;

import ru.ageev.service.*;

import java.io.IOException;
import java.sql.*;


public class App {
    public static void main(String[] args) throws SQLException, IOException {
        String inputFileName = "criteria2.json";
        String outputFileName = "output2.json";
        String type = "stat";

//        String type = args[0];
//        String inputFileName = args[1];
//        String outputFileName = args[2];

        Service service;

        switch (type) {
            case "search" -> service = new SearchService();
            case "stat" -> service = new StatistService();

            default -> service = new ErrorService(type);
        }

        service.startProgram(inputFileName, outputFileName);
    }
}
package ru.ageev;

import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;
import ru.ageev.service.*;

import java.io.IOException;
import java.sql.*;


public class App {
    public static void main(String[] args) throws SQLException, IOException, IncorrectStartEndDateException, IncorrectDateException {
        String inputFileName = "criteria2.json";
        String outputFileName = "output2.json";
        String type = "stat";

//        String type = args[0];
//        String inputFileName = args[1];
//        String outputFileName = args[2];

        Service service;

        if (type.equals(Type.search.name())) {
            service = new SearchService();
        } else if (type.equals(Type.stat.name())) {
            service = new StatistService();
        } else {
            service = new ErrorService(type);
        }

        service.startProgram(inputFileName, outputFileName);
    }
}
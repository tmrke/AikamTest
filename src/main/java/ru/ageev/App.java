package ru.ageev;

import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.json_parser.ReaderCriteria;
import ru.ageev.service.SearchService;
import ru.ageev.service.statist.Statist;

import java.sql.*;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {
        String inputFileName = "criteria1.json";
        String outputFileName = "output.json";

        SearchService searchService = new SearchService();
        searchService.search(inputFileName, outputFileName);

        ReaderCriteria readerCriteria = new ReaderCriteria();

        Statist statist = new Statist();
        statist.getStatistic();

        System.out.println("success");
    }
}




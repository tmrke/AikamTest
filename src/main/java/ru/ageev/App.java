package ru.ageev;


import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.json_parser.ReaderCriteria;
import ru.ageev.service.SearchService;
import ru.ageev.service.statist.Statist;

import java.io.IOException;
import java.sql.*;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException, IOException {
        String inputFileName = "criteria1.json";

        String outputFileName = "output.json";

        SearchService searchService = new SearchService();
        searchService.search(inputFileName, outputFileName);

        ReaderCriteria readerCriteria = new ReaderCriteria();
        List<Criteria> criteria = readerCriteria.getCriteriaList("criteria2.json", Type.STAT);
        StatisticCriteria statisticCriteria = (StatisticCriteria) criteria.get(0);

        Statist statist = new Statist(statisticCriteria);
        statist.getStatistic();

        System.out.println("success");
    }
}




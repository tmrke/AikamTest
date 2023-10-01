package ru.ageev.service;

import ru.ageev.Type;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.json_parser.ReaderCriteria;
import ru.ageev.json_parser.WriteResult;
import ru.ageev.models.result.StatisticResult;


import java.io.IOException;
import java.sql.SQLException;

public class StatistService {
    private ReaderCriteria readerCriteria;
    private WriteResult writeResult;
    private Statist statist;

    public void calculateStatistics(String input, String output) throws IOException, SQLException {
        readerCriteria = new ReaderCriteria();
        StatisticCriteria statisticCriteria = (StatisticCriteria) readerCriteria.getCriteriaList(input, Type.stat).get(0);

        statist = new Statist(statisticCriteria);
        StatisticResult statisticResult = statist.getStatistic();

        writeResult = new WriteResult(statisticResult);

        writeResult.writeOutputFile(output);
    }
}


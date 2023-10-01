package ru.ageev.service;

import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.ErrorCriteria;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.exception.IncorrectDateException;
import ru.ageev.json_convertor.ReaderCriteria;
import ru.ageev.json_convertor.WriterResult;
import ru.ageev.models.result.ErrorResult;
import ru.ageev.models.result.Result;
import ru.ageev.models.result.StatisticResult;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class StatistService implements Service {
    private ReaderCriteria readerCriteria;
    private WriterResult writerResult;
    private Statist statist;

    @Override
    public void startProgram(String input, String output) throws IOException, SQLException {
        readerCriteria = new ReaderCriteria();
        try {
            StatisticCriteria statisticCriteria = (StatisticCriteria) readerCriteria.getCriteriaList(input, Type.stat).get(0);

            statist = new Statist(statisticCriteria);
            StatisticResult statisticResult = statist.getStatistic();

            writerResult = new WriterResult(statisticResult);
        } catch (IncorrectDateException e) {
            writerResult = new WriterResult(new ErrorResult(e.getIncorrectStartDate()));
        } catch (FileNotFoundException e) {
            writerResult = new WriterResult(new ErrorResult("Не найден файл: " + input));
        } finally {
            writerResult.writeOutputFile(output);
        }
    }
}


package ru.ageev.service;

import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;
import ru.ageev.json_convertor.ReaderCriteria;
import ru.ageev.json_convertor.WriterResult;
import ru.ageev.models.result.ErrorResult;
import ru.ageev.models.result.StatisticResult;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StatistService implements Service {
    private ReaderCriteria readerCriteria;
    private WriterResult writerResult;
    private Statist statist;

    @Override
    public void startProgram(String input, String output) throws IOException, SQLException {
        readerCriteria = new ReaderCriteria();
        writerResult = new WriterResult();

        try {
            List<Criteria> criteriaList = readerCriteria.getCriteriaList(input, Type.stat);
            StatisticCriteria statisticCriteria = (StatisticCriteria) criteriaList.get(0);

            statist = new Statist(statisticCriteria);
            StatisticResult statisticResult = statist.getStatistic();

            writerResult.setResult(statisticResult);
        } catch (IncorrectDateException e) {
            writerResult.setResult(new ErrorResult(e.getMessage()));
        } catch (IncorrectStartEndDateException e) {
            writerResult.setResult(new ErrorResult(e.getMessage()));
        } catch (FileNotFoundException e) {
            writerResult.setResult(new ErrorResult("Не найден файл: " + input));
        } finally {
            writerResult.writeOutputFile(output);
        }
    }
}


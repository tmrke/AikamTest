package ru.ageev.service;

import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.ErrorCriteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;
import ru.ageev.exception.NotFoundResultCriteriaException;
import ru.ageev.json_convertor.ReaderCriteria;
import ru.ageev.json_convertor.WriterResult;
import ru.ageev.mapper.CustomerMapper;
import ru.ageev.models.Customer;
import ru.ageev.models.result.ErrorResult;
import ru.ageev.models.result.SearchResult;
import ru.ageev.models.SearchResultItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchService implements Service {
    private ReaderCriteria readerCriteria;
    private WriterResult writerResult;
    private Searcher searcher;


    @Override
    public void startProgram(String input, String output) throws SQLException, IncorrectDateException, IncorrectStartEndDateException {
        readerCriteria = new ReaderCriteria();

        SearchResult searchResult;
        writerResult = new WriterResult();

        try {
            searcher = new Searcher(readerCriteria.getCriteriaList(input, Type.search));
            LinkedHashMap<Criteria, List<CustomerDao>> customersDaoListsByCriteria = searcher.getCustomersDaoByCriteria();

            searchResult = new SearchResult();

            for (Map.Entry<Criteria, List<CustomerDao>> entry : customersDaoListsByCriteria.entrySet()) {
                Criteria criteria = entry.getKey();
                List<CustomerDao> customerDaoList = entry.getValue();

                List<Customer> customerList = new ArrayList<>();

                for (CustomerDao customerDao : customerDaoList) {
                    customerList.add(CustomerMapper.getCustomer(customerDao));
                }

                SearchResultItem searchResultItem = new SearchResultItem(criteria, customerList);
                searchResult.addSearchResultItem(searchResultItem);

                writerResult.setResult(searchResult);
            }
        } catch (NotFoundResultCriteriaException e) {
            writerResult = new WriterResult(new ErrorResult(e.getMessage()));
        } catch (FileNotFoundException e) {
            writerResult = new WriterResult(new ErrorResult("Не найден файл: " + input));
        } catch (IOException e) {
            writerResult = new WriterResult(new ErrorResult("Неверный формат критериев"));
        } finally {
            writerResult.writeOutputFile(output);
        }
    }
}

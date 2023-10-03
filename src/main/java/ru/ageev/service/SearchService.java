package ru.ageev.service;

import ru.ageev.criteria.Criteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;
import ru.ageev.json_convertor.ReaderCriteria;
import ru.ageev.json_convertor.WriterResult;
import ru.ageev.mapper.CustomerMapper;
import ru.ageev.models.Customer;
import ru.ageev.models.result.ErrorResult;
import ru.ageev.models.result.SearchResult;
import ru.ageev.models.SearchResultItem;

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
    public void startProgram(String input, String output) throws SQLException, IOException, IncorrectDateException, IncorrectStartEndDateException {
        readerCriteria = new ReaderCriteria();
        searcher = new Searcher(readerCriteria.getCriteriaList(input, Type.search));

        LinkedHashMap<Criteria, List<CustomerDao>> customersDaoListsByCriteria = searcher.getCustomersDaoByCriteria();

        if(customersDaoListsByCriteria.isEmpty()){
            ErrorResult errorResult = new ErrorResult();
            errorResult.setMessage("Неверный формат критериев");

            writerResult = new WriterResult(errorResult);
            writerResult.writeOutputFile(output);

            return;
        }

        SearchResult searchResult = new SearchResult();

        for (Map.Entry<Criteria, List<CustomerDao>> entry : customersDaoListsByCriteria.entrySet()) {
            Criteria criteria = entry.getKey();
            List<CustomerDao> customerDaoList = entry.getValue();

            List<Customer> customerList = new ArrayList<>();

            for (CustomerDao customerDao : customerDaoList) {
                customerList.add(CustomerMapper.getCustomer(customerDao));
            }

            SearchResultItem searchResultItem = new SearchResultItem(criteria, customerList);
            searchResult.addSearchResultItem(searchResultItem);
        }

        writerResult = new WriterResult(searchResult);
        writerResult.writeOutputFile(output);
    }
}

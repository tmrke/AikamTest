package ru.ageev.service;

import ru.ageev.criteria.Criteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.json_parser.ReaderCriteria;
import ru.ageev.json_parser.WriteResult;
import ru.ageev.mapper.CustomerMapper;
import ru.ageev.models.Customer;
import ru.ageev.models.SearchResult;
import ru.ageev.models.SearchResultItem;
import ru.ageev.service.searcher.Searcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchService {
    private Searcher searcher;
    private ReaderCriteria readerCriteria;
    private WriteResult writeResult;

    public SearchService(){}

    public void search(String input, String output) throws SQLException {
        readerCriteria = new ReaderCriteria();
        searcher = new Searcher(readerCriteria.getCriteriaList(input));

        LinkedHashMap<Criteria, List<CustomerDao>> customersDaoListsByCriteria = searcher.getCustomersDaoByCriteria();

        SearchResult searchResult = new SearchResult();
        List<Customer> customerList = new ArrayList<>();

        for (Map.Entry<Criteria, List<CustomerDao>> entry : customersDaoListsByCriteria.entrySet()) {
            Criteria criteria = entry.getKey();
            List<CustomerDao> customerDaoList = entry.getValue();

            for (CustomerDao customerDao : customerDaoList) {
                customerList.add(CustomerMapper.getCustomer(customerDao));
            }

            SearchResultItem searchResultItem = new SearchResultItem(criteria, customerList);
            searchResult.addSearchResultItem(searchResultItem);
        }

        writeResult = new WriteResult(searchResult);
        writeResult.writeOutputFile(output);
    }
}

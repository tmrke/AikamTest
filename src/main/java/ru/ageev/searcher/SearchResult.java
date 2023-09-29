package ru.ageev.searcher;

import ru.ageev.criteria.Criteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.mapper.CustomerMapper;
import ru.ageev.models.Customer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchResult {
    private final String type = "search";
    private LinkedHashMap<Criteria, List<Customer>> results;

    public SearchResult(LinkedHashMap<Criteria, List<CustomerDao>> customersDaoByCriteria){
        results = new LinkedHashMap<>();

        for (Map.Entry<Criteria, List<CustomerDao>> entry:customersDaoByCriteria.entrySet()){
            Criteria criteria = entry.getKey();
            List<CustomerDao> customerDaoList = entry.getValue();

            List<Customer> customerList = new ArrayList<>();
            for (CustomerDao customerDao : customerDaoList) {
                Customer customer = CustomerMapper.getCustomer(customerDao);
                customerList.add(customer);
            }

            results.put(criteria, customerList);
        }
    }

    public String getType() {
        return type;
    }

    public LinkedHashMap<Criteria, List<Customer>> getResults() {
        return results;
    }

    public void setResults(LinkedHashMap<Criteria, List<Customer>> results) {
        this.results = results;
    }
}

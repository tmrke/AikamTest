package ru.ageev.searcher;

import ru.ageev.criteria.Criteria;
import ru.ageev.models.Customer;

import java.util.List;

public class SearchResultItem {
    private Criteria criteria;
    private List<Customer> results;

    public SearchResultItem(){}

    public SearchResultItem(Criteria criteria, List<Customer> results){
        this.criteria = criteria;
        this.results = results;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<Customer> getResults() {
        return results;
    }

    public void setResults(List<Customer> results) {
        this.results = results;
    }
}

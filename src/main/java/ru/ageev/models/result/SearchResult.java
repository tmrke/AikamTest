package ru.ageev.models.result;


import ru.ageev.service.Type;
import ru.ageev.models.SearchResultItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResult implements Result {
    private final String type = Type.search.name();
    private List<SearchResultItem> results;

    public SearchResult() {
        results = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void addSearchResultItem(SearchResultItem searchResultItem) {
        results.add(searchResultItem);
    }

    public List<SearchResultItem> getResults() {
        return results;
    }

    public void setResults(List<SearchResultItem> results) {
        this.results = results;
    }
}
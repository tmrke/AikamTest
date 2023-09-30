package ru.ageev.searcher;


import java.util.ArrayList;
import java.util.List;

public class SearchResult {
        private final String type = "search";
    private List<SearchResultItem> results;


    public SearchResult() {
        results = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public SearchResult(List<SearchResultItem> results) {
        this.results = results;
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

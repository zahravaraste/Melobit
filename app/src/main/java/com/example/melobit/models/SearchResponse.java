package com.example.melobit.models;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private Integer total;
    private List<SearchResult> results = new ArrayList<SearchResult>();
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public List<SearchResult> getResults() {
        return results;
    }
    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}

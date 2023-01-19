package com.example.melobit.models;

import java.util.ArrayList;
import java.util.List;

public class ArtistResponse {
    private Integer total;
    private List<ArtistResult> results = new ArrayList<ArtistResult>();
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public List<ArtistResult> getResults() {
        return results;
    }
    public void setResults(List<ArtistResult> results) {
        this.results = results;
    }
}

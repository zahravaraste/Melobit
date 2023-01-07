package com.example.melobit.models;

import java.util.ArrayList;
import java.util.List;

public class MusicResponse {
    private Integer total;
    private List<MusicData> results = new ArrayList<MusicData>();
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public List<MusicData> getResults() {
        return results;
    }
    public void setResults(List<MusicData> results) {
        this.results = results;
    }
}

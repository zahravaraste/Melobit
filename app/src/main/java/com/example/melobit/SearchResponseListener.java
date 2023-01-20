package com.example.melobit;

import com.example.melobit.models.SearchResult;

import java.util.List;

public interface SearchResponseListener {
    void didFetch(List<SearchResult> list, String status);
    void didError(String status);
}

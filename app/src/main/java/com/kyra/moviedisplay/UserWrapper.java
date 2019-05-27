package com.kyra.moviedisplay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserWrapper implements Serializable {
    @SerializedName("Search")
    @Expose
    private List<RecyclerViewData> recyclerViewDataList;

    @SerializedName("totalResults")
    @Expose
    private int results;

    @SerializedName("Response")
    @Expose
    private boolean response;

    public List<RecyclerViewData> getRecyclerViewDataList() {
        return recyclerViewDataList;
    }

    public void setRecyclerViewDataList(List<RecyclerViewData> recyclerViewDataList) {
        this.recyclerViewDataList = recyclerViewDataList;
    }
    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}


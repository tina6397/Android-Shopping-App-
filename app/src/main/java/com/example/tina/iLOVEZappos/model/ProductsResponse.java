package com.example.tina.iLOVEZappos.model;


        import com.google.gson.annotations.SerializedName;

        import java.util.List;


public class ProductsResponse {
    @SerializedName("originalTerm")
    private String originalTerm;

    @SerializedName("results")
    private List<Product> results;

    @SerializedName("currentResultCount")
    private int currentResultCount;

    @SerializedName("totalResultCount")

    private int totalResultCount;


    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }

    public int getCurrentResultCount() {
        return currentResultCount;
    }

    public void setCurrentResultCount(int currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    public int getTotalPages() {
        return totalResultCount;
    }

    public void setTotalPages(int totalPages) {
        this.totalResultCount = totalPages;
    }
}
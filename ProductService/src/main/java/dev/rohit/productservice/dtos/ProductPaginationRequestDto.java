package dev.rohit.productservice.dtos;

import dev.rohit.productservice.models.SortParam;

import java.util.List;

public class ProductPaginationRequestDto {
    private int offset;
    private List<SortParam> sortParamsList;
    private String query;
    private int numOfResults;

    public ProductPaginationRequestDto() {
    }

    public ProductPaginationRequestDto(int offset, List<SortParam> sortParamsList, String query, int numOfResults) {
        this.offset = offset;
        this.sortParamsList = sortParamsList;
        this.query = query;
        this.numOfResults = numOfResults;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<SortParam> getSortParamsList() {
        return sortParamsList;
    }

    public void setSortParamsList(List<SortParam> sortParamsList) {
        this.sortParamsList = sortParamsList;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getNumOfResults() {
        return numOfResults;
    }

    public void setNumOfResults(int numOfResults) {
        this.numOfResults = numOfResults;
    }

}

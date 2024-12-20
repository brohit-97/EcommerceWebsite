package dev.rohit.productservice.models;

public class SortParam {
    private String paramName;
    private SortType sortType;

    public SortParam() {
    }

    public SortParam(String paramName, SortType sortType) {
        this.paramName = paramName;
        this.sortType = sortType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}

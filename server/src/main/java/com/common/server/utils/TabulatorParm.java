package com.common.server.utils;

import java.util.List;

public class TabulatorParm {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private Integer page;
    private Integer size;
    private List<String> sorters;
    private List<String> filter;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getSorters() {
        return sorters;
    }

    public void setSorters(List<String> sorters) {
        this.sorters = sorters;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }
}

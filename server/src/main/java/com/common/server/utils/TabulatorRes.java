package com.common.server.utils;

import java.util.List;

public class TabulatorRes<T>{
    private Integer last_page;
    private List<T> data;// 当前页数据

    public TabulatorRes() {
    }

    public TabulatorRes(Integer last_page, List<T> data) {
        this.last_page = last_page;
        this.data = data;
    }

    public Integer getLast_page() {
        return last_page;
    }

    public void setLast_page(Integer last_page) {
        this.last_page = last_page;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

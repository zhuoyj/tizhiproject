package com.common.server.utils;


import java.util.Map;

public class SearchRequest {
    //搜索条件
    private String key;
    //当前页
    private Integer page;
    //每页大小
    private static final Integer DEFAULT_SIZE=20;
    //默认页
    private static final Integer DEFAULT_PAGE=1;
    //过滤条件
    private Map<String,Object> filter;
    //排序
    private Map<String,String> sort;

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if (page==null){
            return DEFAULT_PAGE;
        }
        //获取页码的时候做校验，不能小于1
        return Math.max(DEFAULT_PAGE,page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public static Integer getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public static Integer getDefaultPage() {
        return DEFAULT_PAGE;
    }

    public Integer getSize(){
        return DEFAULT_SIZE;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}
